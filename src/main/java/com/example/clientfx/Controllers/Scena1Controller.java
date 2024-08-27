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
               listTables.getSelectionModel().clearSelection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {

        }
    }


    public void execute(ActionEvent event)  {

        if(loadButton.isSelected()) {
            try {
                client.loadDataOnServer(listTables.getSelectionModel().getSelectedItem());
                mainGui.switchToScene3();
            } catch (IOException | ClassNotFoundException e) {
                new ErrorWindow().showErrorWindow("Data Error","Data Error","An error occurred during the data loading"+"\n"+ "Table selected maybe empty");
                try {
                    mainGui.showScena1();
                } catch (Exception ex) {
                    System.out.println("err");
                }
            }


        }else if (clusterButton.isSelected()) {
            try {
                client.loadDataOnServer(listTables.getSelectionModel().getSelectedItem());
                mainGui.switchToScene2();
            } catch (IOException | ClassNotFoundException e) {
                new ErrorWindow().showErrorWindow("Data Error","Data Error","An error occurred during the data loading"+"\n"+ "Table selected maybe empty");
                try {
                    mainGui.showScena1();
                } catch (Exception ex) {
                    System.out.println("err");
                }
            }

        }
    }
    public void setMainGui(Main mainGui) {
        this.mainGui = mainGui;
    }
}
