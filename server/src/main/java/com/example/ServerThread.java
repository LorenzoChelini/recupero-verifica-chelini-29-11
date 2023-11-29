package com.example;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread {
    Socket client;

    public ServerThread(Socket socket) {
        this.client = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            
            out.writeBytes("Connessione effettuata" + "\n");
            double result = 0;
            do {
                double num1 = Double.parseDouble(in.readLine());
                double num2 = Double.parseDouble(in.readLine());
                String operation = in.readLine();

                
                if (operation.equals("+")) {
                    result = num1 + num2;
                }
                else if (operation.equals("-")) {
                    result = num1 - num2;
                }
                else if (operation.equals("*")){
                    result = num1 * num2;
                }
                else if (operation.equals("/")){
                    if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            result = Double.NaN;
                        }
                }
                else {
                    result = Double.NaN;
                }
                out.writeBytes("Il risultato dell'operazione e': " + result + "\n");
                String choice = in.readLine();
                choice = choice.toUpperCase();
                if (!choice.equals("Y")) {
                    out.writeBytes("Arrivederci!" + "\n");
                    break;
                }
                else {
                    out.writeBytes("Prossimo calcolo!" + "\n");
                }
            } while (true);

            System.out.println("Connessione chiusa con il client.");

            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }
}
