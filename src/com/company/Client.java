package com.company;

import java.io.*;
import java.net.*;
import java.util.Arrays;

class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String username = "";

    void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Welcome to the chatroom client - Version 1");
    }

    void sendMessage(String userInput) throws IOException {
        String [] inputWords = userInput.split(" ");

        switch (inputWords[0]) {
            case "login":
                login(inputWords);
                break;

            case "send":
                send(inputWords);
                break;

            case "newuser":
                newuser(inputWords);
                break;

//            case "who":
//                who(inputWords);
//                break;

            case "logout":
                logout(inputWords);
                break;

            default:
                System.out.println("Command not recognized. Available commands are: 'login', 'send', 'newuser', 'logout', and 'exit'");
                break;
        }
        return;
    }

    private void login(String [] inputWords) {
        if (!username.equals("")) {
            System.out.println("You are already logged in.");
            return;
        }
        if (inputWords.length == 3) {
            out.println(String.join(" ", inputWords));
        } else {
            System.out.println("Invalid number of arguments for login.");
        }
    }

    private void send(String [] inputWords) {
        if (!username.equals("")) {
            if (inputWords.length == 2) {
//                if (inputWords[1].equals("all")) {
//                    // Send to server
//                } else {
//                    // Send to server
//                }
                out.println(String.join(" ", inputWords));
            } else {
                System.out.println("Invalid number of arguments for send.");
            }
        } else {
            System.out.println("You must log in before sending messages.");
        }
    }

    private void newuser(String [] inputWords) {
        if (inputWords.length == 3) {
            if (0 < inputWords[1].length() && inputWords[1].length() < 32) {
                if (inputWords[2].length() > 3 && inputWords[2].length() < 9) {
                    out.println(String.join(" ", inputWords));
                } else {
                    System.out.println("Invalid length for password. Must be between 4 and 8 characters.");
                }
            } else {
                System.out.println("Invalid length for username. Must be between 1 and 31 characters.");
            }
        } else {
            System.out.println("Invalid number of arguments for 'newuser'.");
        }
    }

//    private void who(String [] inputWords) {
//        if (inputWords.length == 1) {
//            // Send to server
//        } else {
//            System.out.println("Invalid number of arguments for 'who'.");
//        }
//    }

    private void logout(String [] inputWords) {
        try {
            stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        System.out.println("You have been logged out and disconnected.");
    }
}
