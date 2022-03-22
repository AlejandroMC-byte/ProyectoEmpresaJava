/*
 * Programa	: ProgramaDAO.java
 * Fecha	: 22/03/2022
 * Objetivo	: Modela el acceso a datos de la tabla comercializacion productos
 * Programador	: Luis Yovany Romo Portilla
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.fachada;

/**
 *
 * @author Alejo
 */
public class comercializacionProductosDAO {
    public comercializacionProductosDAO() {
    }
    /**
     * 
     * @param p Objeto de la clase comercializacionProductos a grabar
     * @return rtdo resultado de la operación grbar
     */
    public int grabarcomercializacionProductos(comercializacionProductos p){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "INSERT INTO comercializacionProductos values (?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, p.getNombreProducto());
            pstm.setInt(2, p.getCantidadVenta());
            
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
    
     /**
     * 
     * @param p Objeto de la clase comercializacionProductos a grabar
     * @return rtdo resultado de la operación modificar
     */
    public int modificarcomercializacionProductos(comercializacionProductos p){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "UPDATE comercializacionProductos " +
                         "SET nombre = ?, nivel = ?, num_creditos = ? "
                    +    "WHERE codigo=?";
            pstm = con.prepareStatement(sql);            
            pstm.setString(1, p.getNombreProducto());
            pstm.setInt(2, p.getCantidadVenta());
           
            
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
            
    /**
     * 
     * @param codigo código del comercializacionProductos a borrar
     * @return rtdo resultado de la operación borrar
     */
    public int borrarcomercializacionProductos(String nombreProducto){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "DELETE FROM comercializacionProductos WHERE codigo = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, nombreProducto);
            rtdo = pstm.executeUpdate(); 
            return rtdo;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        } 
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
    /**
     * 
     * @param codigo codigo del comercializacionProductos a listar, 0 se listaran todos
     * @return ArrayList, lista de objetos comercializacionProductos
     */
    public ArrayList<comercializacionProductos> listadocomercializacionProductoss(String nombreProducto){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<comercializacionProductos> listado = new ArrayList<>();
        try{
            con = fachada.getConnection();
            String sql="";
            if(nombreProducto.equalsIgnoreCase("0")){
                sql = "SELECT * FROM comercializacionProductos ORDER BY codigo";            
            }else{
                sql = "SELECT * FROM comercializacionProductos where codigo = ? "
                    + "ORDER BY codigo";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(nombreProducto != "0"){
                pstm.setString(1, nombreProducto);
            }
            
            rs = pstm.executeQuery();
                        
            comercializacionProductos comercializacionProductos = null;
            while(rs.next()){
                comercializacionProductos = new comercializacionProductos();
                comercializacionProductos.setNombreProducto(rs.getString("Nombre producto"));
                comercializacionProductos.setCantidadVenta(rs.getInt("cantidad venta"));
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return listado;
    }
}
