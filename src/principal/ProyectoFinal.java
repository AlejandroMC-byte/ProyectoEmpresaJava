/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;
import modelo.*;
import servicios.*;
import vista.*;
import controlador.*;
import vista.chat.cliente;
import vista.chat.servidor;
/**
 *
 * @author Alejo
 */
public class ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        productos arrayProductos[]=new productos[6];
        
        arrayProductos[0]=new productos("Papa rellena",1,2000);
        arrayProductos[1]=new productos("Empanadas",1,1000);
        arrayProductos[2]=new productos("Pasteles de carne",1,2000);
        arrayProductos[3]=new productos("Pasteles de pollo",1,2000);
        arrayProductos[4]=new productos("Churros",1,2000);
        arrayProductos[5]=new productos("Dedos",1,2000);
        
        cliente client=new cliente();
        servidor server=new servidor();
        

    }
    
}
