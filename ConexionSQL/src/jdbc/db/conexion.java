package jdbc.db;

import java.sql.*;

public class conexion {
    String db="papeleria";
    String url="jdbc:mysql://localhost:3306/";
    String user="root";
    String pass="";
    String driver="com.mysql.cj.jdbc.Driver";
    
    Connection jdbc;
    public conexion() {}
    public Connection conectar() {
        try {
            Class.forName(driver);
            jdbc = DriverManager.getConnection(url+db, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se puede conectar");
        }
        return jdbc;
    }
    
    public void desconectar() {
        try {
            System.out.println("Conexión finalizada");
            jdbc.close();
        } catch (SQLException e) {
            System.out.println("Error al finalizar la conexión "+ e);
        }
    }
}