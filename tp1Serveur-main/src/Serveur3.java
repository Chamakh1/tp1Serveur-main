import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur3 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("J'attends des connexions...");

            while (true) {
                Socket clientSocket = ss.accept(); // Attendre une nouvelle connexion

                // Créer un thread pour gérer le client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("Nouveau client connecté : " + clientSocket.getInetAddress().getHostAddress());

                InputStream is = clientSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = clientSocket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);

                System.out.println("J'attends une chaîne...");

                String str = br.readLine();
                String rep = "Bonjour Mr. " + str;

                System.out.println("J'envoie la réponse au client : " + rep);
                pw.println(rep); // Envoyer la réponse au client

                clientSocket.close();
                System.out.println("Client déconnecté : " + clientSocket.getInetAddress().getHostAddress());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
