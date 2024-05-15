package BaiTapSocket2_Bai2;

import java.io.*;
import java.net.*;

class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Welcome to the chat server! Please enter your username:");
            username = in.readLine();
            ChatServer.broadcast(username + " has joined the chat.");

            String message;
            while ((message = in.readLine()) != null) {
                ChatServer.broadcast(username + ": " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (username != null) {
                    ChatServer.broadcast(username + " has left the chat.");
                    ChatServer.removeClient(this);
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}

