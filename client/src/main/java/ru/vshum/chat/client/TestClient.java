package ru.vshum.chat.client;

import java.io.IOException;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Network network = new Network(8189);
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        String msgToSend = scanner.nextLine();
                        network.sendMsg(msgToSend);
                        System.out.println(network.readMsg());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
