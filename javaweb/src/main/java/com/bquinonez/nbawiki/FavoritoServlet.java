package com.bquinonez.nbawiki;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Marca / desmarca un campeonato como favorito del usuario logueado. */
@WebServlet("/favorito")
public class FavoritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        int campeonatoId;
        try {
            campeonatoId = Integer.parseInt(req.getParameter("campeonatoId"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int anio = req.getParameter("anio") != null ? Integer.parseInt(req.getParameter("anio")) : 0;

        try (Connection con = DBConnection.getConnection(getServletContext())) {

            boolean yaEsFavorito;
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT 1 FROM favoritos WHERE usuario_id = ? AND campeonato_id = ?")) {
                ps.setInt(1, usuario.getId());
                ps.setInt(2, campeonatoId);
                try (ResultSet rs = ps.executeQuery()) {
                    yaEsFavorito = rs.next();
                }
            }

            if (yaEsFavorito) {
                try (PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM favoritos WHERE usuario_id = ? AND campeonato_id = ?")) {
                    ps.setInt(1, usuario.getId());
                    ps.setInt(2, campeonatoId);
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO favoritos (usuario_id, campeonato_id) VALUES (?, ?)")) {
                    ps.setInt(1, usuario.getId());
                    ps.setInt(2, campeonatoId);
                    ps.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new ServletException("Error actualizando favoritos", e);
        }

        String destino = req.getContextPath() + "/campeonatos" + (anio > 0 ? "#card-" + anio : "");
        resp.sendRedirect(destino);
    }
}
