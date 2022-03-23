package vista;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.fachada;

/**
 *
 * @author Alejo
 */
public class tablasBD {
    marcoBD marco=new marcoBD();
}
class marcoBD extends JFrame{
    public marcoBD(){
        setBounds(300,300,400,400);
        laminaBD milamina=new laminaBD();
        add(milamina);
        setVisible(true);
    }
}
class laminaBD extends JPanel{
    public laminaBD(){
       
        setLayout(new BorderLayout());
        comboTablas=new JComboBox();
        areaInformacion=new JTextArea();
        add(areaInformacion,BorderLayout.CENTER);
        con=(Connection) fachada.getConnection();
        obtenerTablas();
        comboTablas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); To change body of generated methods, choose Tools | Templates.
               String nombreTabla=((String)comboTablas.getSelectedItem());
               
               mostrarInfoTabla(nombreTabla);
            }
        });
        add(comboTablas,BorderLayout.NORTH);
        
        
    }
    public void obtenerTablas(){
        
        ResultSet miresulset=null;
        try{
            DatabaseMetaData datosBD=con.getMetaData();
            miresulset=datosBD.getTables(null, null, null, null);
            
            while(miresulset.next()){
                comboTablas.addItem(miresulset.getString("table_name"));
                //tableName();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String tableName(){
        ArrayList<String> campos=new ArrayList();
        String consulta="SELECT table_name FROM informacion_schema.tables";
        
        try {
            Statement miStatement=con.createStatement();
            ResultSet miResultSet=miStatement.executeQuery(consulta);
            ResultSetMetaData rsBD=miResultSet.getMetaData();
            while(miResultSet.next()){
                 for(String nombreCampo:campos){
                     
                     comboTablas.addItem(miResultSet.getString(nombreCampo));
                     
                 }}
        } catch (SQLException ex) {
            Logger.getLogger(laminaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public void mostrarInfoTabla(String tabla){
        
        ArrayList<String> campos=new ArrayList();
        
        String consulta="SELECT * FROM "+tabla;
        
        try{
            Statement miStatement=con.createStatement();
            
            ResultSet miResultSet=miStatement.executeQuery(consulta);
            
             ResultSetMetaData rsBD=miResultSet.getMetaData();
             
             for(int i=1;i<=rsBD.getColumnCount();i++){
                 
                 campos.add(rsBD.getColumnLabel(i));
             }
             while(miResultSet.next()){
                 for(String nombreCampo:campos){
                     
                     areaInformacion.append(miResultSet.getString(nombreCampo)+" " );
                     
                 }
                 areaInformacion.append("\n");
             }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private Connection con;
    private JComboBox comboTablas;
    private JTextArea areaInformacion;
}
