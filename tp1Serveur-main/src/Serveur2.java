import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur2 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("J�attends une connexion");
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            System.out.println("J�attends une chaine");
            String str = br.readLine();
            String rep = "Bonjour Mr" + str;
            System.out.println("J�envoies la r�ponse");
            pw.println(rep); //os.write(res);
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
