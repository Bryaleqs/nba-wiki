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

/** Indice de todas las franquicias que han jugado unas Finales, ordenadas por titulos. */
@WebServlet("/equipos")
public class EquiposServlet extends HttpServlet {

    private static final String SQL =
        "SELECT e.id, e.nombre, e.ciudad, e.color_principal, e.abreviatura, " +
        "       COUNT(c.id) AS titulos " +
        "FROM equipos e " +
        "LEFT JOIN campeonatos c ON c.equipo_campeon_id = e.id " +
        "GROUP BY e.id, e.nombre, e.ciudad, e.color_principal, e.abreviatura " +
        "ORDER BY titulos DESC, e.ciudad ASC";

    private static final String SQL_APARICIONES =
        "SELECT COUNT(*) AS total FROM campeonatos " +
        "WHERE equipo_campeon_id = ? OR equipo_finalista_id = ?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Equipo> equipos = new ArrayList<>();

        try (Connection con = DBConnection.getConnection(getServletContext())) {

            try (PreparedStatement ps = con.prepareStatement(SQL);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Equipo e = new Equipo();
                    e.setId(rs.getInt("id"));
                    e.setNombre(rs.getString("nombre"));
                    e.setCiudad(rs.getString("ciudad"));
                    e.setColorPrincipal(rs.getString("color_principal"));
                    e.setAbreviatura(rs.getString("abreviatura"));
                    e.setTitulos(rs.getInt("titulos"));
                    equipos.add(e);
                }
            }

            try (PreparedStatement psAp = con.prepareStatement(SQL_APARICIONES)) {
                for (Equipo e : equipos) {
                    psAp.setInt(1, e.getId());
                    psAp.setInt(2, e.getId());
                    try (ResultSet rsAp = psAp.executeQuery()) {
                        if (rsAp.next()) {
                            e.setApariciones(rsAp.getInt("total"));
                        }
                    }
                }
            }

        } catch (SQLException e) {
            throw new ServletException("Error consultando equipos", e);
        }

        req.setAttribute("equipos", equipos);
        req.getRequestDispatcher("/WEB-INF/views/equipos.jsp").forward(req, resp);
    }
}
