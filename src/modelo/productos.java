package modelo;

import java.util.ArrayList;

/**
 *
 * @author Alejo
 */
public class productos {
    private String nombre;
    private int cantidad;
    private int precio;
    
    
    private productos(){
        
    }
    public productos(String nombre,int cantidad, int precio){
        this.nombre=nombre;
        this.cantidad=cantidad;
        this.precio=precio;
        
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad += (cantidad);
    }
    
    
}
