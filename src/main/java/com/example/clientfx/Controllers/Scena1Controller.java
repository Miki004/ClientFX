package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class Scena1Controller {


    @FXML
    public TextField tableSpace;
    private Main mainApp;
    @FXML
    private TextField ipField;
    @FXML
    private TextField portField;
    @FXML
    private RadioButton loadFromFileButton;
    @FXML
    private RadioButton mineFromDbButton;
    @FXML
    private Button MyButton;
    @FXML
    private TextArea outputArea;
    private MainTest client;


    public void riceviTabella(ActionEvent e) {
        client.setNameFile(tableSpace.getText());
        SendTable();
    }

    public void writeOnTableSpace() {
        client.setNameFile(tableSpace.getText());
    }

    public void writeOnArea(String word) {
        outputArea.appendText(word + "\n");
    }

    public MainTest getClient() {
        return client;
    }

    public void SendTable() {
        try {
            client.loadDataOnServer();
            outputArea.appendText("Sent data: " + "\n");
        } catch (IOException | ClassNotFoundException e) {
            outputArea.appendText("Failed to send data: " + e.getMessage() + "\n");
        }
    }

    public void initializeConnection(String ip, int port) {
        ipField.setText(ip);
        portField.setText(String.valueOf(port));
        try {
            outputArea.appendText(ip + port);
            client = new MainTest(ip, port);
            client.setMainScene(this);
            outputArea.appendText("Connected to server at " + ip + ":" + port + "\n");
        } catch (IOException e) {
            outputArea.appendText("Failed to connect: " + e.getMessage() + "\n");
        }
    }

    @FXML
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void executeSelectedOption() {
        if (client == null) {
            outputArea.appendText("You must connect to the server first.\n");
            return;
        }
        if (loadFromFileButton.isSelected()) {
            mainApp.switchToScene2();
        } else if (mineFromDbButton.isSelected()) {
            mainApp.switchToScene3();
        }
    }

}
