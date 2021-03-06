package javachatonline;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadAscolta extends Thread {

    private Condivisa cond;
    private DatagramSocket server;
    private byte[] buffer;
    private DatagramPacket packet;
    private byte[] dataReceived;
    private String messaggioRicevuto;


    public ThreadAscolta(Condivisa c) throws SocketException {
        this.cond = c;
        server = new DatagramSocket(12345);
        buffer = new byte[1500];
        packet = new DatagramPacket(buffer, buffer.length);
        dataReceived = new byte[1500];
        messaggioRicevuto = "";
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
                Logger.getLogger(ThreadAscolta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
