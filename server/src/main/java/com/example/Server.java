package com.example;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Server in ascolto sulla porta 5555...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata da: " + clientSocket.getInetAddress());

                // Creazione di un nuovo thread per gestire la connessione
                ServerThread calculatorThread = new ServerThread(clientSocket);
                calculatorThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
