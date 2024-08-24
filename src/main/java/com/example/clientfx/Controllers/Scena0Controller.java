package com.example.clientfx.Controllers;

import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Scena0Controller {
    @FXML
    private TextField ipField;
    @FXML
    private TextField portField;
    private Main mainGui;


    public void start(ActionEvent event) throws Exception {
        mainGui.setIp(ipField.getText());
        mainGui.setPort(Integer.parseInt(portField.getText()));
        mainGui.showScena1();
    }

    public void setMainGui(Main mainGui) {
        this.mainGui = mainGui;
    }
}
