package ru.vshum.chat.server;

import javafx.event.ActionEvent;

public class ServerController {


    public void startServer(ActionEvent actionEvent) {
        new Server(8189);
    }
}
