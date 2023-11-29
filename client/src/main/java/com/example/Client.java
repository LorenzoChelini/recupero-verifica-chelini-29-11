package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner input = new Scanner(System.in);

            String message = "";
            String userInput = "";
            System.out.println(in.readLine());

            do {
                System.out.print("Inserisci il primo numero: ");
                userInput = input.nextLine();
                out.writeBytes(userInput + "\n");
                System.out.print("Inserisci il secondo: ");
                userInput = input.nextLine();
                out.writeBytes(userInput + "\n");
                System.out.println("Scegli l'operazione da eseguire tra i due numeri (+, -, *, /)");
                userInput = input.nextLine();
                out.writeBytes(userInput + "\n");

                // Ricevi e leggi la risposta dal server
                message = in.readLine();
                System.out.println(message);
                System.out.println("Vuoi effettuare un nuovo calcolo (Y/N)?");
                userInput= input.nextLine();
                out.writeBytes(userInput + "\n");
                message = in.readLine();
                System.out.println(message);

            } while (!message.equals("Arrivederci!"));

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

