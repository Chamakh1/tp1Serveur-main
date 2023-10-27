
import java.net.*;
import java.io.*;

public class Serveur1 {
    public static void main(String[] args){
        try {
            ServerSocket ss = new ServerSocket(8082);
            System.out.println("J�attends une connexion");
            Socket s = ss.accept();
            InputStream is=s.getInputStream();
            OutputStream os=s.getOutputStream();
            System.out.println("J�attends un nombre");
            int nb=is.read();
            int res=nb*3;
            System.out.println("J�envoies la r�ponse");
            os.write(res);
            s.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
