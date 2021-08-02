package ru.vshum.chat.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    TextArea textArea;

    @FXML
    TextField messageField;

    public void sendMessage(ActionEvent actionEvent) {
        textArea.appendText(messageField.getText() + "\n");
        messageField.clear();
        messageField.requestFocus();
    }
}
