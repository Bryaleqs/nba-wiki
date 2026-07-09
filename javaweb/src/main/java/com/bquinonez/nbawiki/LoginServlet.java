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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nombreUsuario = req.getParameter("nombreUsuario");
        String password = req.getParameter("password");

        try (Connection con = DBConnection.getConnection(getServletContext())) {

            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT id, nombre_usuario, email, password_hash, salt FROM usuarios WHERE nombre_usuario = ?")) {
                ps.setString(1, nombreUsuario);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        mostrarError(req, resp, "Usuario o contraseña incorrectos.");
                        return;
                    }

                    String hashGuardado = rs.getString("password_hash");
                    String salt = rs.getString("salt");

                    if (!PasswordUtil.verificar(password, salt, hashGuardado)) {
                        mostrarError(req, resp, "Usuario o contraseña incorrectos.");
                        return;
                    }

                    Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nombre_usuario"), rs.getString("email"));
                    HttpSession session = req.getSession(true);
                    session.setAttribute("usuario", usuario);
                }
            }

        } catch (SQLException e) {
            throw new ServletException("Error verificando credenciales", e);
        }

        resp.sendRedirect(req.getContextPath() + "/campeonatos");
    }

    private void mostrarError(HttpServletRequest req, HttpServletResponse resp, String mensaje)
            throws ServletException, IOException {
        req.setAttribute("error", mensaje);
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }
}
