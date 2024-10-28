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

        Socket mySocket = new Socket("localhost", 3000);
        Scanner sc = new Scanner(System.in);
        String stringRed = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());


        System.out.println("Connessione effettuata. Digita ESCI per uscire.");
        System.out.println("Inserisci la nota da memorizzare o digita LISTA per visualizzare le note salvate.  ");


        do {
            System.out.println("Inserisci una nota: ");
            String miaStringa = sc.nextLine();

            if (miaStringa.equals("ESCI")) {
                System.out.println("Disconnessione Effettuata");
                out.writeBytes("!" + "\n");
                break;
            } else if (miaStringa.equals("LISTA")) {
                System.out.println("Ecco la lista:");
                out.writeBytes("?" + "\n");
                stringRed = in.readLine();
                System.out.println(stringRed);
                do {
                    stringRed = in.readLine();
                    if (!stringRed.equals("@")) {
                        System.out.println(stringRed);
                    } else {
                        break;
                    }

                } while (!stringRed.equals("@"));
            } else {
                out.writeBytes(miaStringa + "\n");
                stringRed = in.readLine();
                if (stringRed.equals("OK")) {
                    System.out.println("NOTA SALVATA");
                    
                }
            }

        } while (true);

        mySocket.close();

        sc.close();
    }
}