package ru.vshum.chat.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerStarter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/server_gui.fxml"));
        primaryStage.setTitle("Server Controller");
        primaryStage.setScene(new Scene(root, 400, 150));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
