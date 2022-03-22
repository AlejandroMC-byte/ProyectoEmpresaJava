/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class empleadoDAO {
    public empleadoDAO() {
    }
    /**
     * 
     * @param p Objeto de la clase empleado a grabar
     * @return rtdo resultado de la operación grbar
     */
    public int grabarempleado(empleado p){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "INSERT INTO empleado values (?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,p.getCodigo());
            pstm.setString(2, p.getNombre());
            pstm.setInt(3, p.getSalario());
            pstm.setInt(4,p.getComision());
            
            
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
     * @param p Objeto de la clase empleado a grabar
     * @return rtdo resultado de la operación modificar
     */
    public int modificarempleado(empleado p){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "UPDATE empleado " +
                         "SET nombre = ?, nivel = ?, num_creditos = ? "
                    +    "WHERE codigo=?";
            pstm = con.prepareStatement(sql);            
            pstm.setInt(1,p.getCodigo());
            pstm.setString(2, p.getNombre());
            pstm.setInt(3, p.getSalario());
            pstm.setInt(4,p.getComision());
           
            
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
     * @param codigo código del empleado a borrar
     * @return rtdo resultado de la operación borrar
     */
    public int borrarempleado(String codigo){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "DELETE FROM empleado WHERE codigo = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, codigo);
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
     * @param codigo codigo del empleado a listar, 0 se listaran todos
     * @return ArrayList, lista de objetos empleado
     */
    public ArrayList<empleado> listadoempleados(String codigo){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<empleado> listado = new ArrayList<>();
        try{
            con = fachada.getConnection();
            String sql="";
            if(codigo.equalsIgnoreCase("0")){
                sql = "SELECT * FROM empleado ORDER BY codigo";            
            }else{
                sql = "SELECT * FROM empleado where codigo = ? "
                    + "ORDER BY codigo";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(codigo != "0"){
                pstm.setString(1, codigo);
            }
            
            rs = pstm.executeQuery();
                        
            empleado empleado = null;
            while(rs.next()){
                empleado = new empleado();
                empleado.setCodigo(rs.getInt("codigo"));
                empleado.setNombre(rs.getString("Nombre"));
                empleado.setSalario(rs.getInt("Salario"));
                empleado.setComision(rs.getInt("Comision"));
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
