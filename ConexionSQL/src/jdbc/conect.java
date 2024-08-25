package jdbc;

import jdbc.db.conexion;
import java.sql.*;

public class conect {
    
    public static void main(String[] args) {
        conexion jdbc = new conexion();
        Statement statement;
        ResultSet rows;
        try {
            statement = jdbc.conectar().createStatement();
            rows = statement.executeQuery("SELECT * FROM users");
            System.out.println("Registros");
            while(rows.next()){
                System.out.println("ID: "+rows.getInt("id_user") + " " + ("Nombre: "+rows.getString("username")));
            }
            jdbc.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al mostras los datos" +e);
        }
    }
}