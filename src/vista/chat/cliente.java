package vista.chat;

import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejo
 */
public class cliente {
    marcoCliente inicio=new marcoCliente();
    
}

class marcoCliente extends JFrame{
    
    public marcoCliente(){
        setBounds(600,300,280,350);
        LaminaMarcoCliente milamina=new LaminaMarcoCliente();
        add(milamina);
        setVisible(true);
    }
}
class LaminaMarcoCliente extends JPanel implements Runnable{
    public LaminaMarcoCliente() {
        nick=new JTextField(5);
        add(nick);
        JLabel texto =new JLabel("-CHAT-");
        add(texto);
        
        ip=new JTextField(8);
        add(ip);
        campochat=new JTextArea(12,20);
        add(campochat);
        campo1=new JTextField(20);
        add(campo1);
        miboton=new JButton("Enviar");
        
        
        EnviaTexto mievento=new EnviaTexto();
        
        miboton.addActionListener(mievento);
        
        add(miboton);
        
        Thread mihilo =new Thread(this);
        
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            ServerSocket servidorCliente =new ServerSocket(9999);
            
            Socket cliente;
            
            paqueteEnvio paqueteRecibido;
            
            while(true){
                
                cliente=servidorCliente.accept();
                
                ObjectInputStream flujoEntrada=new ObjectInputStream(cliente.getInputStream());
                
                paqueteRecibido=(paqueteEnvio) flujoEntrada.readObject();
                
                campochat.append("\n"+paqueteRecibido.getNick()+": "+paqueteRecibido.getMensaje()+" para: "+paqueteRecibido.getIp());
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    }
    
    private class EnviaTexto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
 
            try {
                Socket misocket=new Socket("172.19.192.1",9999);
                
                paqueteEnvio datos=new paqueteEnvio();
                
                datos.setNick(nick.getText());
                datos.setIp(ip.getText());
                datos.setMensaje(campo1.getText());
                
                ObjectOutputStream flujoSalida=new ObjectOutputStream(misocket.getOutputStream());
                
                flujoSalida.writeObject(datos);
                misocket.close();
                /*DataOutputStream flujoSalida=new DataOutputStream(misocket.getOutputStream());
            
                flujoSalida.writeUTF(campo1.getText());
                flujoSalida.close();*/
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }      
    }
    
    private JTextField nick,ip;
    private JTextField campo1;
    private JTextArea campochat;
    private JButton miboton;
}

class paqueteEnvio implements Serializable{
    
    private String nick,ip,mensaje;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
