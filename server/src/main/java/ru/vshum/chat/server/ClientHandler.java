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

    ClientHandler(Server server, Socket socket) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true){
                    // Получаем сообщение от одного клиента
                    String msgFromClient = in.readUTF();
                    System.out.println("Message frm client: \"" + msgFromClient + "\"");
                    // Отправляем его всем клиентам
                    server.broadcastMethod(msgFromClient);
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
