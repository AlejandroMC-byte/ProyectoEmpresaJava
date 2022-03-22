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
    
    
    public productos(){
    }

    public int getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
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
