package com.bquinonez.nbawiki;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Pagina de wiki para una sola franquicia: historial completo y rival mas frecuente. */
@WebServlet("/equipo")
public class EquipoServlet extends HttpServlet {

    private static final String SQL_EQUIPO =
        "SELECT id, nombre, ciudad, color_principal, abreviatura FROM equipos WHERE id = ?";

    private static final String SQL_APARICIONES =
        "SELECT c.anio, c.resultado_serie, c.equipo_campeon_id, " +
        "       ec.nombre AS campeon_nombre, ec.ciudad AS campeon_ciudad, " +
        "       ef.nombre AS finalista_nombre, ef.ciudad AS finalista_ciudad " +
        "FROM campeonatos c " +
        "JOIN equipos ec ON c.equipo_campeon_id = ec.id " +
        "JOIN equipos ef ON c.equipo_finalista_id = ef.id " +
        "WHERE c.equipo_campeon_id = ? OR c.equipo_finalista_id = ? " +
        "ORDER BY c.anio DESC";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int equipoId;
        try {
            equipoId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/equipos");
            return;
        }

        Equipo equipo = null;
        List<Aparicion> apariciones = new ArrayList<>();
        Map<String, Integer> conteoRivales = new HashMap<>();

        try (Connection con = DBConnection.getConnection(getServletContext())) {

            try (PreparedStatement ps = con.prepareStatement(SQL_EQUIPO)) {
                ps.setInt(1, equipoId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        equipo = new Equipo();
                        equipo.setId(rs.getInt("id"));
                        equipo.setNombre(rs.getString("nombre"));
                        equipo.setCiudad(rs.getString("ciudad"));
                        equipo.setColorPrincipal(rs.getString("color_principal"));
                        equipo.setAbreviatura(rs.getString("abreviatura"));
                    }
                }
            }

            if (equipo == null) {
                resp.sendRedirect(req.getContextPath() + "/equipos");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(SQL_APARICIONES)) {
                ps.setInt(1, equipoId);
                ps.setInt(2, equipoId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        boolean fueCampeon = rs.getInt("equipo_campeon_id") == equipoId;

                        Aparicion ap = new Aparicion();
                        ap.setAnio(rs.getInt("anio"));
                        ap.setCampeon(fueCampeon);
                        ap.setResultadoSerie(rs.getString("resultado_serie"));

                        String rivalNombre = fueCampeon ? rs.getString("finalista_nombre") : rs.getString("campeon_nombre");
                        String rivalCiudad = fueCampeon ? rs.getString("finalista_ciudad") : rs.getString("campeon_ciudad");
                        ap.setRivalNombre(rivalNombre);
                        ap.setRivalCiudad(rivalCiudad);
                        apariciones.add(ap);

                        String claveRival = rivalCiudad + " " + rivalNombre;
                        conteoRivales.merge(claveRival, 1, Integer::sum);
                    }
                }
            }

            equipo.setTitulos((int) apariciones.stream().filter(Aparicion::isCampeon).count());
            equipo.setApariciones(apariciones.size());

        } catch (SQLException e) {
            throw new ServletException("Error consultando el equipo", e);
        }

        String rivalHistorico = null;
        int maxEnfrentamientos = 0;
        for (Map.Entry<String, Integer> entry : conteoRivales.entrySet()) {
            if (entry.getValue() > maxEnfrentamientos) {
                maxEnfrentamientos = entry.getValue();
                rivalHistorico = entry.getKey();
            }
        }

        req.setAttribute("equipo", equipo);
        req.setAttribute("apariciones", apariciones);
        req.setAttribute("rivalHistorico", rivalHistorico);
        req.setAttribute("rivalHistoricoVeces", maxEnfrentamientos);
        req.getRequestDispatcher("/WEB-INF/views/equipo.jsp").forward(req, resp);
    }
}
