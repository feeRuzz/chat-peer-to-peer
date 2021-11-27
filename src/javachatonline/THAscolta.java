/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatonline;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kazakevych_vladyslav
 */
public class THAscolta extends Thread {

    private Condivisa cond;
    private DatagramSocket server;
    private byte[] buffer;
    private DatagramPacket packet;
    private byte[] dataReceived;
    private String messaggioRicevuto = new String(dataReceived, 0, packet.getLength());


    public THAscolta(Condivisa c) throws SocketException {
        this.cond = c;
        server = new DatagramSocket(12345);
        buffer = new byte[1500];
        packet = new DatagramPacket(buffer, buffer.length);
        dataReceived = new byte[1500];
        messaggioRicevuto = new String();
    }

    @Override
    public void run() {

        while (true) {
            try {
                server.receive(packet);
                dataReceived = packet.getData();
                messaggioRicevuto = new String(dataReceived, 0, packet.getLength());
                gestioneMessaggio.controllaMessaggio(messaggioRicevuto);
                
            } catch (IOException ex) {
                Logger.getLogger(THAscolta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }

}
