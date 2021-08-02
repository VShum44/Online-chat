import java.io.IOException;

public class TestClient {

    static Network network;

    public static void main(String[] args) {
        try {
            network = new Network(8189);
            network.sendMsg("Привет");
            System.out.println(network.readMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
