package javachatonline;


public final class gestioneMessaggio {

    private static String messaggio;

    private gestioneMessaggio() {
        messaggio = "";
    }

    
    //a;NOME_MITTENTE; 
    public static void controllaMessaggio(String m) {
        messaggio = m;

        String[] split = m.split(";");

        if (split[0].equals("a")) {

            String risposta = "y;PC1";
            
            
            
        } else if (split[0].equals("y")) {

        } else if (split[0].equals("n")) {

        } else if (split[0].equals("m")) {

        } else if (split[0].equals("c")) {

        }

    }
}
