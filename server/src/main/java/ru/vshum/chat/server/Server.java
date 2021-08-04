package ru.vshum.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<ClientHandler> clients;

    public Server(int port) {
        try (ServerSocket server = new ServerSocket(8189)) {
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает полключения клиента...");
                Socket socket = server.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("Hi from Server");

                System.out.println("Клиент подключился");
                clients.add(new ClientHandler(this,socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



        public void broadcastMethod(String msg){
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMessageToClient(msg);
        }
    }
}
