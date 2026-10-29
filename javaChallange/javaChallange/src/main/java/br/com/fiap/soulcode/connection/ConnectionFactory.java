
package br.com.fiap.soulcode.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL =
            "jdbc:h2:mem:soulguide;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";

    private static final String USUARIO = "sa";

    private static final String SENHA = "";

    public static Connection abrirConexao() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USUARIO,
                SENHA
        );
    }
}
