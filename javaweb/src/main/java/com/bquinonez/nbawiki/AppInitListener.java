package com.bquinonez.nbawiki;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.h2.tools.RunScript;

/**
 * Antes, el script de inicializacion se ejecutaba via el parametro INIT
 * de la URL de conexion, lo que lo re-ejecutaba en CADA conexion (cada
 * request), borrando usuarios y favoritos que ya se hubieran guardado.
 *
 * Este listener corre UNA sola vez, cuando arranca la aplicacion, y solo
 * crea/llena las tablas si todavia no existen. Asi, los datos de los
 * usuarios sobreviven mientras la app siga corriendo.
 */
@WebListener
public class AppInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection con = DBConnection.getConnection(sce.getServletContext())) {

            boolean tablasYaExisten;
            try (Statement st = con.createStatement()) {
                st.executeQuery("SELECT 1 FROM equipos LIMIT 1");
                tablasYaExisten = true;
            } catch (SQLException e) {
                tablasYaExisten = false;
            }

            if (!tablasYaExisten) {
                try (InputStream is = getClass().getResourceAsStream("/nba_wiki_h2.sql")) {
                    if (is == null) {
                        throw new IOException("No se encontro nba_wiki_h2.sql en el classpath");
                    }
                    Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
                    RunScript.execute(con, reader);
                }
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException("Error inicializando la base de datos nba_wiki", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Nada que limpiar al apagar la app.
    }
}
