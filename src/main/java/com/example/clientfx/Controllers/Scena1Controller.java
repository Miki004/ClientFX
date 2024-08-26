package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class Scena1Controller {

    @FXML
    private RadioButton loadButton;
    @FXML
    private RadioButton clusterButton;
    private TextArea area;
    private MainTest client;
    @FXML
    private ListView<String> listTables;
    private Main mainGui;

    public void initializeConnection(String ip, int port) {
        try {
            client = new MainTest(ip,port);
            mainGui.setClient(client);
            try {
               listTables.getItems().addAll(client.request());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {

        }
    }


    public void execute(ActionEvent event) throws IOException{
        if(loadButton.isSelected()) {
            try {
                client.loadDataOnServer(listTables.getSelectionModel().getSelectedItem());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            mainGui.switchToScene3();

        }else if (clusterButton.isSelected()) {
            try {
                client.loadDataOnServer(listTables.getSelectionModel().getSelectedItem());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            mainGui.switchToScene2();
        }
    }
    public void setMainGui(Main mainGui) {
        this.mainGui = mainGui;
    }
}
