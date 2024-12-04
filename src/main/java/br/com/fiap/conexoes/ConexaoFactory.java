package br.com.fiap.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    
   
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL"; 
    private static final String USER = ""; 
    private static final String PASSWORD = ""; 

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
     
        Class.forName("oracle.jdbc.driver.OracleDriver");
       
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
