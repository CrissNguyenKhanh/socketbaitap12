package BaiTapSocket2_Bai1;

import java.io.*;
import java.net.*;

public class NumberClient {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String number;
            while ((number = in.readLine()) != null) {
                System.out.println("Received number from server: " + number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

