package com.example.clientfx.Controllers;

import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Scena0Controller {
    public Label errorLabel;
    @FXML
    private TextField ipField;
    @FXML
    private TextField portField;
    private Main mainGui;

    public void start(ActionEvent event) throws Exception {

        String ip=ipField.getText();
        Integer port=null;
        try {
            port=Integer.parseInt(portField.getText());
        }catch (NumberFormatException e) {
            errorLabel.setVisible(true);
            errorLabel.setText("Please enter a number");
        }

        if(tryConnection(ip,port)) {
            mainGui.setIp(ip);
            mainGui.setPort(port);
            mainGui.showScena1();
        }else {
            ErrorWindow error= new ErrorWindow();
            error.showErrorWindow("Connection Error", "Connection Error","An error has occurred during the connection with the server");
            ipField.clear();
            portField.clear();
        }
    }

    private boolean tryConnection(String ip, Integer port) throws IOException {
        Socket s;
        try {
            s=new Socket(ip,port);
            s.close();
            return true;
        }catch ( IOException | NullPointerException | IllegalArgumentException e) {
            return false;
        }
    }

    public void setMainGui(Main mainGui) {
        this.mainGui = mainGui;
    }
}
