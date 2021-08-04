package ru.vshum.chat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ClientController {

    @FXML
    TextArea mainTextArea;

    @FXML
    TextField messageField, portField, passwordField;

    @FXML
    HBox sighInPanel, sendMessagePane;

    private boolean authenticated;
    private Network network;

    private void isAuthenticated(boolean authenticated){
        sighInPanel.setVisible(!authenticated);
        sighInPanel.setManaged(authenticated);
        sendMessagePane.setVisible(authenticated);
        sendMessagePane.setManaged(authenticated);
    }

    /*
    Отправка сообщений из интерфейса
     */
    public void sendMessage(ActionEvent actionEvent) {

        String msgToServer = messageField.getText().trim();
        //Защита против отправки пустого сообщения
        if (msgToServer.equals("")){
            return;
        }
        messageField.clear();
        messageField.requestFocus();
        //Отправка соообщения серверу
        try {
            network.sendMsg(msgToServer);
        } catch (IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Не удалось отправить сообщение" +
                    "\nПроверьте сетевое подключение",ButtonType.APPLY);
            alert.showAndWait();
        }
    }

    public void connect(ActionEvent actionEvent) {

        try {
            //Подключение к серверу
            network = new Network(Integer.parseInt(portField.getText()));
            //Получение сообщений и от сервера в новом  потоке
            //И вывод их на основную панель
           Thread incomingMessagesProcessing  =  new Thread(new Runnable() {
                @Override
                public void run() {
                  try {
                    while (true){
                        String msgFromServer = network.readMsg();
                        mainTextArea.appendText(msgFromServer + "\n");
                    }
                  } catch (IOException e) {
                      Platform.runLater(() ->{
                          Alert alert = new Alert(Alert.AlertType.WARNING,"Соединение с сервером прервано"
                                  ,ButtonType.APPLY);
                          alert.showAndWait();
                      });
                    }
                  finally {
                      network.close();
                  }
                }
            });
           incomingMessagesProcessing.setDaemon(true);
            incomingMessagesProcessing.start();


        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Невозможно подключиться к серверу" +
                    "\nПроверьте сетевое подключение"
                    ,ButtonType.APPLY);
            alert.showAndWait();
        }
    }
}
