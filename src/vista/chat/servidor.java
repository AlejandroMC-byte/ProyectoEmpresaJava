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
    
}
class MarcoServidor extends JFrame implements Runnable{
    public MarcoServidor(){
        setBounds(1200,300,200,350);
        JPanel milamina =new JPanel();
        milamina.setLayout(new BorderLayout());
        areatexto=new JTextArea();
        milamina.add(areatexto,BorderLayout.CENTER);
        add(milamina);
        setVisible(true);
        
        Thread mihilo=new Thread(this);
        mihilo.start();
    }
    private JTextArea areatexto;

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
                
                misocket.close();
            }
            
            
        } catch (IOException |ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
        
    }
}
