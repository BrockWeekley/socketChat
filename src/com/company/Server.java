package com.company;

import java.io.*;
import java.net.*;

class Server extends Thread {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    void startServer(int port) throws IOException {
        System.out.println("Starting Server");
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Started server on port " + port);

        String clientString;
        while(( clientString = in.readLine()) != null) {
            String [] clientWords = clientString.split(" ");
            System.out.println("Your command is: ");
            for (String word: clientWords) {
                System.out.println(word + " ");
            }
        }

    }

    void stopServer() throws IOException {
        clientSocket.close();
        serverSocket.close();
        out.close();
        in.close();
    }

    public void run() {
        try {
            startServer(10842);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
