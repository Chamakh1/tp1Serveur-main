import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur4{
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Le serveur attend des connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté depuis : " + clientSocket.getInetAddress());

                // Créez un thread pour gérer le client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
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
                InputStream is = clientSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                OutputStream os = clientSocket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);

                // Attendre et lire la chaîne envoyée par le client
                String str = br.readLine();

                if (str != null) {
                    String reponse = "Bonjour Mr " + str;
                    // Envoyer la réponse au client
                    pw.println(reponse);
                    System.out.println("Réponse envoyée : " + reponse);
                }

                // Fermer la connexion avec le client
                clientSocket.close();
                System.out.println("Client déconnecté depuis : " + clientSocket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
