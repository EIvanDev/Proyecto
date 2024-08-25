/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario_bd_mysql;

import java.sql.*;
import javax.swing.JOptionPane;

public class CConexion {
    
    String db="papeleria";
    String url="jdbc:mysql://localhost:3306/";
    String user="root";
    String pass="";
    String driver="com.mysql.cj.jdbc.Driver";
    
    Connection conectar = null;
    
    public Connection estableceConexion() {
        
        try {
            Class.forName(driver);
            conectar =  DriverManager.getConnection(url+db, user, pass);
            /*JOptionPane.showMessageDialog(null,"Se realizo la conexión correctamente");*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en la conexión en la base de datos "+ e.toString());
        }
        return conectar;
    }
    
    
}
