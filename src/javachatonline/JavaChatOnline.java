package javachatonline;

import java.io.IOException;
import java.net.SocketException;


public class JavaChatOnline {

    public static void main(String[] args) throws SocketException, IOException {
     
        
        Condivisa cond = new Condivisa();
        ThreadAscolta tha = new ThreadAscolta(cond);
        
        
        tha.start();
        
        
    }
    
}
