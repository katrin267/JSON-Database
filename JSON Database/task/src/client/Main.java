package client;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;


public class Main {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 23456;

    public static void main(String[] args) {

        String sentMsg = new RequestParser(args).serializeToJson();

        try (
                Socket socket = new Socket(InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Client started!");
            output.writeUTF(sentMsg); // sending message to the server
            System.out.println("Sent: " + sentMsg);

            String receivedMsg = input.readUTF();
            System.out.println("Received: " + receivedMsg); // response message

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}