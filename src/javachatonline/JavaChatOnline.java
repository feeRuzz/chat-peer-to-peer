/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatonline;

import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author kazakevych_vladyslav
 */
public class JavaChatOnline {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, IOException {
        // TODO code application logic here
        
        Condivisa cond = new Condivisa();
        THAscolta tha = new THAscolta(cond);
        
        
        tha.start();
        
        
    }
    
}