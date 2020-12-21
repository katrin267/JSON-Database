package server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    final static String EXIT = "exit";

    ServerSocket serverSocket;
    Socket socket;
    JsonObject jobject;

    public Server(Socket socket, ServerSocket serverSocket) {
        this.socket = socket;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            String userInput = input.readUTF();

            JsonElement jelement = JsonParser.parseString(userInput);
            jobject = jelement.getAsJsonObject();

            String type = jobject.get("type").getAsString();
            String answer = getAnswer(type);

            output.writeUTF(answer);

            if (type.equals("exit")) {
                serverSocket.close();
            }

            input.close();
            output.close();

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getAnswer(String type) {

        switch (type) {
            case "get":
                return Main.cellBase.getString(jobject);
            case "set":
                return Main.cellBase.setString(jobject);
            case "delete":
                return Main.cellBase.delString(jobject);
            case "exit":
                return Main.cellBase.exit();
            default:
                return "";
        }
    }

}
