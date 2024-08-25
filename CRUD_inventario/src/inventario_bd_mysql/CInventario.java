/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario_bd_mysql;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;

public class CInventario {

    int id;
    String codigoProducto;
    String nombreProducto;
    int cantidad;
    int precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() { 
        return cantidad;
    }

    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad;
    }

    public int getPrecio() { 
        return precio;
    }

    public void setPrecio(int precio) { 
        this.precio = precio;
    }
    
    //guardar elementos en el inventario
    public void insertarProducto(JTextField paramCodigo, JTextField paramNombreProd, JTextField paramCantidad, JTextField paramPrecio) {

        setCodigoProducto(paramCodigo.getText());
        setNombreProducto(paramNombreProd.getText());
        
        try {
            setCantidad(Integer.parseInt(paramCantidad.getText()));
            setPrecio(Integer.parseInt(paramPrecio.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de formato en cantidad o precio: " + e.getMessage());
            return; // Salir del método si hay un error de conversión
        }

        CConexion objetoConexion = new CConexion();

        String consulta = "insert into inventario (cod_producto, nombre_producto, cantidad, precio) values (?,?,?,?);";

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);

            cs.setString(1, getCodigoProducto());
            cs.setString(2, getNombreProducto());
            cs.setInt(3, getCantidad());
            cs.setInt(4, getPrecio());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se ingresaron correctamente los datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ingresaron los datos, error: " + e.toString());
        }
    }
    
    //Mostrar la tabla de la base de datos
    public void mostrarInventario(JTable paramTablaTotalInvetario){
        
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalInvetario.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Fecha Ingreso");
        
        paramTablaTotalInvetario.setModel(modelo);
        
        sql = "select * from inventario;";
        
        String [] datos = new String [6];
        Statement st;
        
        try {
            st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                
                modelo.addRow(datos);
            }
            paramTablaTotalInvetario.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
        }
    }
    
    //Seleccionar elementos de la tabla de la bese de datos
    public void seleccionarInventario(JTable paramTablaInventario, JTextField paramCodigo, JTextField paramNombreProd, JTextField paramCantidad, JTextField paramPrecio, JTextField paramId) {
        try {
            int fila = paramTablaInventario.getSelectedRow();

            if (fila >= 0) {
                paramCodigo.setText((String) paramTablaInventario.getValueAt(fila, 1));
                paramNombreProd.setText((String) paramTablaInventario.getValueAt(fila, 2));
                paramCantidad.setText(String.valueOf(paramTablaInventario.getValueAt(fila, 3)));
                paramPrecio.setText(String.valueOf(paramTablaInventario.getValueAt(fila, 4)));
                paramId.setText(String.valueOf(paramTablaInventario.getValueAt(fila, 0)));
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
        }
    }
    
    //Actualizar registro
    public void modificarInventario(JTextField paramCodigo, JTextField paramNombreProd, JTextField paramCantidad, JTextField paramPrecio, JTextField paramId) {
        setId(Integer.parseInt(paramId.getText()));
        setCodigoProducto(paramCodigo.getText());
        setNombreProducto(paramNombreProd.getText());
        setCantidad(Integer.parseInt(paramCantidad.getText()));
        setPrecio(Integer.parseInt(paramPrecio.getText()));
        
        CConexion objetoConexion = new CConexion();

        String consulta = "UPDATE inventario SET cod_producto = ?, nombre_producto = ?, cantidad = ?, precio = ? WHERE id_producto = ?;";

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getCodigoProducto());
            cs.setString(2, getNombreProducto());
            cs.setInt(3, getCantidad());
            cs.setInt(4, getPrecio());
            cs.setInt(5, getId());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Datos actualizados");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.toString());
        }
    }
    
    //Eliminar un regristro
    public void eliminarInventario(JTextField paramId){
        
        setId(Integer.parseInt(paramId.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM inventario WHERE id_producto = ?;";
        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getId());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino registro del inventario correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar: " + e.toString());
        }
    }
}