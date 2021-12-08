package javachatonline;

import com.sun.jdi.connect.spi.Connection;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private Connection connection;
    private static ModelloCliente model;
    private static InterfacciaCliente gui;
    private volatile boolean isConnect = false; 

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    
    public static void main(String[] args) {
        Client client = new Client();
        model = new ModelloCliente();
        gui = new InterfacciaCliente(client);
        gui.initFrameClient();
        while (true) {
            if (client.isConnect()) {
                client.nameUserRegistration();
                client.receiveMessageFromServer();
                client.setConnect(false);
            }
        }
    }

    
    protected void connectToServer() {
        
        if (!isConnect) {
            while (true) {
                try {
                    
                    String addressServer = gui.getServerAddressFromOptionPane();
                    int port = gui.getPortServerFromOptionPane();
                    
                    Socket socket = new Socket(addressServer, port);
                    connection = new Connection(socket) {};
                    isConnect = true;
                    gui.addMessage("Service: connesso al server.\n");
                    break;
                } catch (Exception e) {
                    gui.errorDialogWindow("Errore, l'indirizzo o la porta sbagliata");
                    break;
                }
            }
        } else gui.errorDialogWindow("Sei gia connesso!");
    }

    
    protected void nameUserRegistration() {
        while (true) {
            try {
                Message message = connection.receive();
                
                if (message.getTypeMessage() == MessageType.REQUEST_NAME_USER) {
                    String nameUser = gui.getNameUser();
                    connection.send(new Message(MessageType.USER_NAME, nameUser));
                }
               
                if (message.getTypeMessage() == MessageType.NAME_USED) {
                    gui.errorDialogWindow("Questo nome gia esiste, scegli altro");
                    String nameUser = gui.getNameUser();
                    connection.send(new Message(MessageType.USER_NAME, nameUser));
                }
                
                if (message.getTypeMessage() == MessageType.NAME_ACCEPTED) {
                    gui.addMessage("Service: il tuo nome accettato!\n");
                    model.setUsers(message.getListUsers());
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                gui.errorDialogWindow("Errore nella registrazione del nome. Riprova!");
                try {
                    connection.close();
                    isConnect = false;
                    break;
                } catch (IOException ex) {
                    gui.errorDialogWindow("Errore nella chiusura");
                }
            }

        }
    }

    
    protected void sendMessageOnServer(String text) {
        try {
            connection.send(new Message(MessageType.TEXT_MESSAGE, text));
        } catch (Exception e) {
            gui.errorDialogWindow("Errore nel invio del messaggio");
        }
    }

    
    protected void receiveMessageFromServer() {
        while (isConnect) {
            try {
                Message message = connection.receive();
                
                if (message.getTypeMessage() == MessageType.TEXT_MESSAGE) {
                    gui.addMessage(message.getTextMessage());
                }
                
                if (message.getTypeMessage() == MessageType.USER_ADDED) {
                    model.addUser(message.getTextMessage());
                    gui.refreshListUsers(model.getUsers());
                    gui.addMessage(String.format("Service: user %s connesso alla chat.\n", message.getTextMessage()));
                }
                
                if (message.getTypeMessage() == MessageType.REMOVED_USER) {
                    model.removeUser(message.getTextMessage());
                    gui.refreshListUsers(model.getUsers());
                    gui.addMessage(String.format("Service: user %s disconnesso.\n", message.getTextMessage()));
                }
            } catch (Exception e) {
                gui.errorDialogWindow("Errore nel ricevere pacchetti dal server.");
                setConnect(false);
                gui.refreshListUsers(model.getUsers());
                break;
            }
        }
    }

    
    protected void disableClient() {
        try {
            if (isConnect) {
                connection.send(new Message(MessageType.DISABLE_USER));
                model.getUsers().clear();
                isConnect = false;
                gui.refreshListUsers(model.getUsers());
            } else gui.errorDialogWindow("Sei gia disconnesso.");
        } catch (Exception e) {
            gui.errorDialogWindow("Service: errore nella disconnessione.");
        }
    }
}
