import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8189)) {
            System.out.println("Сервер ожидает полключения клиента...");
            Socket socket = server.accept();
            System.out.println("Клиент подключился");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while(true){
                String msg = in.readUTF();
                System.out.println("Message frm client: \"" + msg + "\"");
                out.writeUTF("Got it: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
