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

/**
 *
 * @author kazakevych_vladyslav
 */
public class Condivisa {

    private DatagramSocket server;
    private byte[] buffer;
    private DatagramPacket packet;
    private byte[] dataReceived;
    private String messaggioRicevuto;

    //System.out.println (messaggioRicevuto);
    public Condivisa() throws SocketException, IOException {
        this.packet = new DatagramPacket(buffer, buffer.length);
        this.buffer = new byte[1500];
        this.server = new DatagramSocket(12345);
        this.dataReceived = new byte[1500];
        this.messaggioRicevuto = new String();
    }

    public void serverReceive() throws IOException {
        server.receive(packet);
    }

    public void aggiorna(String mess) {
        
    }

}
