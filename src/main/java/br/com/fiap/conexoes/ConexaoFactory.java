package br.com.fiap.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    
    // URL de conexão, usuário e senha do banco de dados
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL"; // Altere se necessário
    private static final String USER = "RM557774"; // Seu usuário
    private static final String PASSWORD = "080403"; // Sua senha

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Carregar o driver do Oracle
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // Retornar a conexão
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
