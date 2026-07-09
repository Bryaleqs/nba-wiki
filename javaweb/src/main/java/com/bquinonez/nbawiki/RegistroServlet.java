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

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nombreUsuario = req.getParameter("nombreUsuario");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        // Validaciones basicas del lado servidor (nunca confiar solo en el HTML)
        if (nombreUsuario == null || nombreUsuario.trim().length() < 3) {
            mostrarError(req, resp, "El nombre de usuario debe tener al menos 3 caracteres.");
            return;
        }
        if (email == null || !email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            mostrarError(req, resp, "Ingresa un correo valido.");
            return;
        }
        if (password == null || password.length() < 6) {
            mostrarError(req, resp, "La contraseña debe tener al menos 6 caracteres.");
            return;
        }
        if (!password.equals(password2)) {
            mostrarError(req, resp, "Las contraseñas no coinciden.");
            return;
        }

        try (Connection con = DBConnection.getConnection(getServletContext())) {

            // Verifica que no exista ya el usuario o el correo
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM usuarios WHERE nombre_usuario = ? OR email = ?")) {
                ps.setString(1, nombreUsuario);
                ps.setString(2, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        mostrarError(req, resp, "Ese usuario o correo ya esta registrado.");
                        return;
                    }
                }
            }

            String salt = PasswordUtil.generarSalt();
            String hash = PasswordUtil.hashPassword(password, salt);

            try (PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO usuarios (nombre_usuario, email, password_hash, salt) VALUES (?, ?, ?, ?)")) {
                ps.setString(1, nombreUsuario.trim());
                ps.setString(2, email.trim());
                ps.setString(3, hash);
                ps.setString(4, salt);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new ServletException("Error registrando usuario", e);
        }

        resp.sendRedirect(req.getContextPath() + "/login?registrado=1");
    }

    private void mostrarError(HttpServletRequest req, HttpServletResponse resp, String mensaje)
            throws ServletException, IOException {
        req.setAttribute("error", mensaje);
        req.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(req, resp);
    }
}
