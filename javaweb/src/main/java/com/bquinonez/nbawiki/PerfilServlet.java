package com.bquinonez.nbawiki;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        try (Connection con = DBConnection.getConnection(getServletContext())) {

            // Lista de equipos para el selector de "equipo favorito"
            List<Equipo> equipos = new ArrayList<>();
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT id, nombre, ciudad, color_principal, abreviatura FROM equipos ORDER BY ciudad")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Equipo e = new Equipo();
                        e.setId(rs.getInt("id"));
                        e.setNombre(rs.getString("nombre"));
                        e.setCiudad(rs.getString("ciudad"));
                        e.setColorPrincipal(rs.getString("color_principal"));
                        e.setAbreviatura(rs.getString("abreviatura"));
                        equipos.add(e);
                    }
                }
            }
            req.setAttribute("equipos", equipos);

            // Si tiene equipo favorito, trae sus estadisticas (titulos, años, ultimo titulo)
            if (usuario.getEquipoFavoritoId() != null) {
                try (PreparedStatement ps = con.prepareStatement(
                        "SELECT ec.nombre, ec.ciudad, ec.color_principal, ec.abreviatura, " +
                        "       COUNT(c.id) AS titulos, MAX(c.anio) AS ultimo_anio, MIN(c.anio) AS primer_anio " +
                        "FROM equipos ec LEFT JOIN campeonatos c ON c.equipo_campeon_id = ec.id " +
                        "WHERE ec.id = ? GROUP BY ec.id, ec.nombre, ec.ciudad, ec.color_principal, ec.abreviatura")) {
                    ps.setInt(1, usuario.getEquipoFavoritoId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            req.setAttribute("equipoFavNombre", rs.getString("nombre"));
                            req.setAttribute("equipoFavCiudad", rs.getString("ciudad"));
                            req.setAttribute("equipoFavColor", rs.getString("color_principal"));
                            req.setAttribute("equipoFavAbbr", rs.getString("abreviatura"));
                            req.setAttribute("equipoFavTitulos", rs.getInt("titulos"));
                            req.setAttribute("equipoFavUltimo", rs.getInt("ultimo_anio"));
                            req.setAttribute("equipoFavPrimero", rs.getInt("primer_anio"));
                        }
                    }
                }

                // Años en que el equipo favorito salio campeon (para la mini linea de tiempo)
                List<Integer> aniosTitulo = new ArrayList<>();
                try (PreparedStatement ps = con.prepareStatement(
                        "SELECT anio FROM campeonatos WHERE equipo_campeon_id = ? ORDER BY anio")) {
                    ps.setInt(1, usuario.getEquipoFavoritoId());
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            aniosTitulo.add(rs.getInt("anio"));
                        }
                    }
                }
                req.setAttribute("aniosTitulo", aniosTitulo);
                req.setAttribute("equipoFavId", usuario.getEquipoFavoritoId());

                // Posicion en el ranking de titulos (1 = el que mas titulos tiene)
                try (PreparedStatement ps = con.prepareStatement(
                        "SELECT COUNT(*) + 1 AS posicion FROM (" +
                        "  SELECT equipo_campeon_id, COUNT(*) AS titulos FROM campeonatos " +
                        "  GROUP BY equipo_campeon_id" +
                        ") ranking WHERE ranking.titulos > (" +
                        "  SELECT COUNT(*) FROM campeonatos WHERE equipo_campeon_id = ?" +
                        ")")) {
                    ps.setInt(1, usuario.getEquipoFavoritoId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            req.setAttribute("equipoFavPosicion", rs.getInt("posicion"));
                        }
                    }
                }
            }

            // Campeonatos que este usuario marco como favoritos
            List<Campeonato> favoritos = new ArrayList<>();
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT c.anio, ec.nombre AS campeon, ec.abreviatura, ec.color_principal " +
                    "FROM favoritos f " +
                    "JOIN campeonatos c ON f.campeonato_id = c.id " +
                    "JOIN equipos ec ON c.equipo_campeon_id = ec.id " +
                    "WHERE f.usuario_id = ? ORDER BY c.anio DESC")) {
                ps.setInt(1, usuario.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Campeonato c = new Campeonato();
                        c.setAnio(rs.getInt("anio"));
                        c.setCampeon(rs.getString("campeon"));
                        c.setAbreviatura(rs.getString("abreviatura"));
                        c.setColorPrincipal(rs.getString("color_principal"));
                        favoritos.add(c);
                    }
                }
            }
            req.setAttribute("favoritos", favoritos);

            // Insignias (gamificacion simple basada en la actividad del usuario)
            List<String> insignias = calcularInsignias(favoritos, usuario);
            req.setAttribute("insignias", insignias);

        } catch (SQLException e) {
            throw new ServletException("Error cargando el perfil", e);
        }

        req.getRequestDispatcher("/WEB-INF/views/perfil.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        int equipoId;
        try {
            equipoId = Integer.parseInt(req.getParameter("equipoFavoritoId"));
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/perfil");
            return;
        }

        try (Connection con = DBConnection.getConnection(getServletContext());
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE usuarios SET equipo_favorito_id = ? WHERE id = ?")) {
            ps.setInt(1, equipoId);
            ps.setInt(2, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Error actualizando el equipo favorito", e);
        }

        usuario.setEquipoFavoritoId(equipoId);
        session.setAttribute("usuario", usuario);

        resp.sendRedirect(req.getContextPath() + "/perfil");
    }

    /** Insignias simples segun cuanto ha interactuado el usuario con la wiki. */
    private List<String> calcularInsignias(List<Campeonato> favoritos, Usuario usuario) {
        List<String> insignias = new ArrayList<>();

        if (favoritos.isEmpty()) {
            insignias.add("Novato: aun no marcas favoritos");
        }
        if (favoritos.size() >= 1) insignias.add("Fan: marcaste tu primer favorito");
        if (favoritos.size() >= 5) insignias.add("Coleccionista: 5+ campeonatos favoritos");
        if (favoritos.size() >= 10) insignias.add("Historiador: 10+ campeonatos favoritos");

        boolean tieneRetro = favoritos.stream().anyMatch(c -> c.getAnio() < 1980);
        if (tieneRetro) insignias.add("Old School: aprecias el basquet de antes de 1980");

        boolean tieneReciente = favoritos.stream().anyMatch(c -> c.getAnio() >= 2020);
        if (tieneReciente) insignias.add("Al dia: sigues la era moderna");

        if (usuario.getEquipoFavoritoId() != null) insignias.add("Hincha: elegiste un equipo favorito");

        return insignias;
    }
}
