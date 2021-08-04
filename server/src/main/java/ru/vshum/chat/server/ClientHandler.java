package ru.vshum.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String nickName;
    private String password;

    ClientHandler(Server server, Socket socket) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        String firstMessageFromClient = in.readUTF();
        nickName = firstMessageFromClient.split(" ")[0];
//        password = firstMessageFromClient.split(" ")[1];

        new Thread(() -> {
            try {
                while (true){
                    // Получаем сообщение от одного клиента
                    String msgFromClient = in.readUTF();
                    System.out.println("Message from client " + nickName + ": \"" + msgFromClient + "\"");
                    // Отправляем его всем клиентам
                    server.broadcastMethod(nickName + ": " + msgFromClient);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessageToClient(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
