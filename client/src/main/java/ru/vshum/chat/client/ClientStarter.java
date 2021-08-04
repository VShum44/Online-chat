package ru.vshum.chat.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientStarter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/client_gui.fxml"));
        primaryStage.setTitle("YourTalk");
        primaryStage.setScene(new Scene(root, 500, 575));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
