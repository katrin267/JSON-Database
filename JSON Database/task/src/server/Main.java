package server;

import com.google.gson.Gson;
import com.sun.source.tree.IfTree;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static String address = "127.0.0.1";
    static int port = 23456;
    static CellBase cellBase = new CellBase();
    static ExecutorService executor = Executors.newFixedThreadPool(4);
    static boolean isClosed;

    public static void main(String[] args) {


        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));) {
            System.out.println("Server started!");
            while (!server.isClosed()) {

                Socket socket = server.accept(); // accepting a new client

                executor.execute(new Server(socket, server));
//                System.out.println("Connection accepted.");
            }
            executor.shutdown();

        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}
