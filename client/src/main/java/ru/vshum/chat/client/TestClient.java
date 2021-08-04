package ru.vshum.chat.client;

import java.io.IOException;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                }
            }
        });
        t.setDaemon(true);
        t.start();

        for (int i = 10; i <15 ; i++) {
            System.out.println(i);
        }
    }
}
