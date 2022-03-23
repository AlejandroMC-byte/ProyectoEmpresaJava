package vista.chat;

import java.awt.BorderLayout;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Alejo
 */
public class servidor {
    MarcoServidor server = new MarcoServidor();
}
class MarcoServidor extends JFrame implements Runnable{
    public MarcoServidor(){
        setBounds(1200,300,200,350);
        JPanel milamina =new JPanel();
        milamina.setLayout(new BorderLayout());
        areatexto=new JTextArea();
        milamina.add(areatexto,BorderLayout.CENTER);
        add(milamina);
        setVisible(false);
        
        Thread mihilo=new Thread(this);
        mihilo.start();
    }
    private JTextArea areatexto;
    private File archivo;
    private FileWriter escribir;
     private PrintWriter linea;
    @Override
    public void run() {
        
        try {
            ServerSocket servidor=new ServerSocket(9999);
            
            String nick,ip,mensaje;
            
            paqueteEnvio paqueteRecibido;
            
            while(true){
            
                Socket misocket =servidor.accept();

                ObjectInputStream paqueteEntrada=new ObjectInputStream(misocket.getInputStream());

                paqueteRecibido=(paqueteEnvio) paqueteEntrada.readObject();
                
                nick=paqueteRecibido.getNick();
                ip=paqueteRecibido.getIp();
                mensaje=paqueteRecibido.getMensaje();
                
                //areatexto.append("\n"+mensajeTexto);
                areatexto.append("\n"+nick+": "+mensaje+" para: "+ip);
                
                archivo=new File("servidor.txt");
                if(!archivo.exists()){
                    try{
                        archivo.createNewFile();
                        
                        escribir =new FileWriter(archivo,true);
                        linea =new PrintWriter(escribir);
                        //Escribimos en el archivo
                        linea.println(mensaje);
                        linea.close();
                        escribir.close();
                        
                    }catch(IOException ex){
                        System.out.println("ERROR");
                    }
                }else{
                    try{
                        
                        escribir =new FileWriter(archivo,true);
                        linea =new PrintWriter(escribir);
                        //Escribimos en el archivo
                        linea.println(mensaje);
                        linea.close();
                        escribir.close();
                    }catch(IOException ex){
                         System.out.println("ERROR");

                    }
                }
                
                Socket enviaDestinatario=new Socket(ip,9999);
                
                ObjectOutputStream paqueteReenvio= new ObjectOutputStream(enviaDestinatario.getOutputStream());
                
                paqueteReenvio.writeObject(paqueteRecibido);
                
                enviaDestinatario.close();
                
                misocket.close();
            }
            
            
        } catch (IOException |ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
        
    }
}
class archivoTXT{
    private File archivo;
    private FileWriter escribir;
    private PrintWriter linea;
    
    public archivoTXT(String nombre){
        if(!archivo.exists()){
            try {
                this.archivo= new File(nombre+".txt");
                this.archivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(archivoTXT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
        }
    }
}
