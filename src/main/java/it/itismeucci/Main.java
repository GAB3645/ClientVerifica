package it.itismeucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Client startato");
        
        Socket mySocket = new Socket("localhost", 5672);
        System.out.println("Il client si è collegato");
        Scanner sc = new Scanner(System.in);
        String stringRed = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());

        do {
            System.out.println("Inserisci stringa: ");
            String outputString = sc.nextLine();
            if (outputString.equals("!")) {
                System.out.println("Disconnessione client");
                break;
            }
            System.out.println("\n1)Trasformare maiuscolo\n2)Trasformare maiuscolo\n3)Ribaltare\n4)Contare caratteri\nINSERISCI MODALITA': ");
            String outputOperazione = sc.nextLine();
            out.writeBytes(outputString + "\n");
            out.writeBytes(outputOperazione + "\n");
            stringRed = in.readLine();
            System.out.println("Stringa ricevuta: " + stringRed);
        } while (true);

        mySocket.close();

        sc.close();
    }
}