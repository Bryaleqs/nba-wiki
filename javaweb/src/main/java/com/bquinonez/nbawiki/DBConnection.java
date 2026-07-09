package com.bquinonez.nbawiki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;

/** Abre una conexion JDBC usando los datos definidos en web.xml. */
public class DBConnection {

    public static Connection getConnection(ServletContext ctx) throws SQLException {
        String url = ctx.getInitParameter("db.url");
        String user = ctx.getInitParameter("db.user");
        String password = ctx.getInitParameter("db.password");

        // Detecta el driver segun el tipo de URL, asi puedes cambiar
        // entre H2 (embebida, por defecto) y MySQL solo editando web.xml.
        try {
            if (url != null && url.startsWith("jdbc:h2:")) {
                Class.forName("org.h2.Driver");
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el driver JDBC. Revisa el pom.xml.", e);
        }

        return DriverManager.getConnection(url, user, password);
    }
}
