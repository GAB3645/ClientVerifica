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

        Scanner sc = new Scanner(System.in);
        String stringa = sc.nextLine();

        Socket mySocket = new Socket("localhost", 5637);
        System.out.println("Il client si è collegato");

        BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());

        out.writeBytes(stringa + '\n');
        String stringaRicevuta = in.readLine();

        System.out.println(stringaRicevuta);

        mySocket.close();

        sc.close();
    }
}