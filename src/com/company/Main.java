package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Server server = new Server();
        Client client = new Client();
        try {
            server.start();
            client.startConnection("127.0.0.1", 10842);
            String input = scanner.nextLine();
            while (true) {
                if (input.toLowerCase().equals("exit") || input.toLowerCase().equals("end")) {
                    break;
                } else {
                    client.sendMessage(input.toLowerCase());
                }
                input = scanner.nextLine();
            }
            client.stopConnection();
            server.stopServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
