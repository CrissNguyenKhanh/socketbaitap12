package BaiTapSocket2_Bai2;

import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter your username: ");
            String username = userInput.readLine();
            serverOut.println(username);

            Thread serverListener = new Thread(new ServerListener(serverIn));
            serverListener.start();

            String input;
            while ((input = userInput.readLine()) != null) {
                serverOut.println(input);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerListener implements Runnable {
    private BufferedReader serverIn;

    public ServerListener(BufferedReader serverIn) {
        this.serverIn = serverIn;
    }

    public void run() {
        try {
            String message;
            while ((message = serverIn.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
