package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Scena3Controller {
    @FXML
    public Button loadButton;
    @FXML
    public TextField loadField;
    @FXML
    public TextArea loadArea;
    private MainTest client;
    private Main main;

    public void initialize() {
        // Blocca l'input da tastiera nell'areaIP
        loadArea.setEditable(false); // Rende l'area non modificabile
        loadArea.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            event.consume(); // Blocca l'evento della tastiera
        });
    }

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

    public void setMain(Main main) {
        this.main=main;
    }

}

