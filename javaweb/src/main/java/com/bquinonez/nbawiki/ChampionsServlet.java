package com.bquinonez.nbawiki;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Trae todos los campeonatos (con equipo campeon, rival, MVP y roster)
 * y los deja listos en el request para que index.jsp los pinte.
 * Soporta filtro opcional ?conferencia=Este|Oeste
 */
@WebServlet("/campeonatos")
public class ChampionsServlet extends HttpServlet {

    private static final String SQL_CAMPEONATOS =
        "SELECT c.id, c.anio, c.resultado_serie, c.conferencia_campeon AS conferencia, " +
        "       ec.nombre AS campeon, ec.ciudad, ec.abreviatura, ec.color_principal, " +
        "       ef.nombre AS finalista, j.nombre AS mvp " +
        "FROM campeonatos c " +
        "JOIN equipos ec ON c.equipo_campeon_id = ec.id " +
        "JOIN equipos ef ON c.equipo_finalista_id = ef.id " +
        "LEFT JOIN jugadores j ON c.mvp_finales_id = j.id " +
        "%s " +
        "ORDER BY c.anio DESC";

    private static final String SQL_ROSTER =
        "SELECT j.nombre FROM campeonato_roster cr " +
        "JOIN jugadores j ON cr.jugador_id = j.id " +
        "WHERE cr.campeonato_id = ?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String conferencia = req.getParameter("conferencia"); // Este | Oeste | null
        String decadaParam = req.getParameter("decada");      // ej. "1990" | null

        List<Campeonato> lista = new ArrayList<>();
        Map<Integer, Campeonato> idAAnio = new LinkedHashMap<>();

        List<String> condiciones = new ArrayList<>();
        List<Object> parametros = new ArrayList<>();

        if ("Este".equals(conferencia) || "Oeste".equals(conferencia)) {
            condiciones.add("c.conferencia_campeon = ?");
            parametros.add(conferencia);
        }

        Integer decadaInicio = null;
        try {
            if (decadaParam != null && !decadaParam.isEmpty()) {
                decadaInicio = Integer.parseInt(decadaParam);
                condiciones.add("c.anio >= ? AND c.anio < ?");
                parametros.add(decadaInicio);
                parametros.add(decadaInicio + 10);
            }
        } catch (NumberFormatException ignored) {
            decadaInicio = null;
        }

        String whereClause = condiciones.isEmpty() ? "" : "WHERE " + String.join(" AND ", condiciones);

        try (Connection con = DBConnection.getConnection(getServletContext())) {

            try (PreparedStatement ps = con.prepareStatement(String.format(SQL_CAMPEONATOS, whereClause))) {
                for (int i = 0; i < parametros.size(); i++) {
                    ps.setObject(i + 1, parametros.get(i));
                }
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Campeonato c = new Campeonato();
                        c.setId(rs.getInt("id"));
                        c.setAnio(rs.getInt("anio"));
                        c.setCampeon(rs.getString("campeon"));
                        c.setCiudad(rs.getString("ciudad"));
                        c.setAbreviatura(rs.getString("abreviatura"));
                        c.setColorPrincipal(rs.getString("color_principal"));
                        c.setConferencia(rs.getString("conferencia"));
                        c.setFinalista(rs.getString("finalista"));
                        c.setResultadoSerie(rs.getString("resultado_serie"));
                        String mvpNombre = rs.getString("mvp");
                        c.setMvp(mvpNombre != null ? mvpNombre : "Premio no existia aun (anterior a 1969)");
                        lista.add(c);
                        idAAnio.put(rs.getInt("id"), c);
                    }
                }
            }

            // Segunda consulta: roster de cada campeonato
            try (PreparedStatement psRoster = con.prepareStatement(SQL_ROSTER)) {
                for (Map.Entry<Integer, Campeonato> entry : idAAnio.entrySet()) {
                    psRoster.setInt(1, entry.getKey());
                    try (ResultSet rsRoster = psRoster.executeQuery()) {
                        List<String> roster = new ArrayList<>();
                        while (rsRoster.next()) {
                            roster.add(rsRoster.getString("nombre"));
                        }
                        entry.getValue().setRoster(roster);
                    }
                }
            }

            // Tercera consulta: total de favoritos por campeonato (para todos los visitantes)
            try (PreparedStatement psTotales = con.prepareStatement(
                    "SELECT campeonato_id, COUNT(*) AS total FROM favoritos GROUP BY campeonato_id")) {
                try (ResultSet rsTotales = psTotales.executeQuery()) {
                    while (rsTotales.next()) {
                        Campeonato c = idAAnio.get(rsTotales.getInt("campeonato_id"));
                        if (c != null) {
                            c.setTotalFavoritos(rsTotales.getInt("total"));
                        }
                    }
                }
            }

            // Cuarta consulta: cuales campeonatos marco el usuario logueado (si hay sesion)
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("usuario") != null) {
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                Set<Integer> favoritosUsuario = new HashSet<>();
                try (PreparedStatement psFav = con.prepareStatement(
                        "SELECT campeonato_id FROM favoritos WHERE usuario_id = ?")) {
                    psFav.setInt(1, usuario.getId());
                    try (ResultSet rsFav = psFav.executeQuery()) {
                        while (rsFav.next()) {
                            favoritosUsuario.add(rsFav.getInt("campeonato_id"));
                        }
                    }
                }
                for (Map.Entry<Integer, Campeonato> entry : idAAnio.entrySet()) {
                    entry.getValue().setEsFavorito(favoritosUsuario.contains(entry.getKey()));
                }
            }

        } catch (SQLException e) {
            throw new ServletException("Error consultando la base de datos nba_wiki", e);
        }

        req.setAttribute("campeonatos", lista);
        req.setAttribute("conferenciaActiva", conferencia == null ? "todos" : conferencia);
        req.setAttribute("decadaActiva", decadaInicio == null ? "todos" : String.valueOf(decadaInicio));
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
