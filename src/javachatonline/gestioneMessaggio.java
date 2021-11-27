/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatonline;

/**
 *
 * @author kazakevych_vladyslav
 */
public final class gestioneMessaggio {

    private static String messaggio;

    private gestioneMessaggio() {
        messaggio = "";
    }

    public static void controllaMessaggio(String m) {
        messaggio = m;

        String[] split = m.split(";");

        if (split[0].equals("a")) {

        } else if (split[0].equals("y")) {

        } else if (split[0].equals("n")) {

        } else if (split[0].equals("m")) {

        } else if (split[0].equals("c")) {

        }

    }
}
