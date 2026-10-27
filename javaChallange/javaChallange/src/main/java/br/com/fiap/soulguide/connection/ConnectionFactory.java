package br.com.fiap.soulguide.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";

    private static final String USUARIO =
            "rm571336";

    private static final String SENHA =
            "070807";

    public static Connection abrirConexao()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USUARIO,
                SENHA
        );
    }
}

