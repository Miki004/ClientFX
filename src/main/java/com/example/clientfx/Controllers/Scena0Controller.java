package com.example.clientfx.Controllers;

import com.example.clientfx.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Scena0Controller {
    public Label errorLabel;
    @FXML
    private TextField ipField;
    @FXML
    private TextField portField;
    private Main mainGui;

    public void start(ActionEvent event) throws Exception {
        String ip=ipField.getText();
        int port = Integer.parseInt(portField.getText());
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 100);
            if (socket.isConnected()) {
                mainGui.setIp(ip);
                mainGui.setPort(port);
                socket.close();
                mainGui.showScenaSetDB();
            } else {
                new ErrorWindow().showErrorWindow("CONNECTION ERROR", "CONNECTION ERROR", "Please check your parameters");
                ipField.clear();
                portField.clear();
            }
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            new ErrorWindow().showErrorWindow("CONNECTION ERROR", "CONNECTION ERROR", "Please check your parameters");
            ipField.clear();
            portField.clear();
        }


    }

    public void setMainGui(Main mainGui) {
        this.mainGui = mainGui;
    }
}
