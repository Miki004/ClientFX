package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Scena3Controller {
    @FXML
    public Button loadButton;
    @FXML
    public TextField loadField;
    @FXML
    public TextArea loadArea;
    private MainTest client;

    public void setClient(MainTest client) {
        this.client=client;
    }

    public void loadClustering(ActionEvent event) {
        try {
            loadArea.clear();
            client.loadDedrogramFromFileOnServer(loadField.getText());
            loadArea.appendText(client.getOutput());
        } catch (IOException | ClassNotFoundException e) {
            new ErrorWindow().showErrorWindow("Loading Error","Loading Error","An error occurs during the clustering uploading");
        }
    }
}

