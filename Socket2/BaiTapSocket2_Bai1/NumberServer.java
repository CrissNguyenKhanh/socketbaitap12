package BaiTapSocket2_Bai1;

import java.io.*;
import java.net.*;

public class NumberServer {
    public static void main(String[] args) {
        final int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            for (int i = 1; i <= 1000; i++) {
                out.println(i);
                Thread.sleep(1000); // Gửi mỗi số mỗi giây
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
