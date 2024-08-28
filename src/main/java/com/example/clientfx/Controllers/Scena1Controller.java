package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.util.List;

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
    private static int count=0;
    private static List<String> list;

    public void initializeTables() {
        if (count==0) {
            try {
                list=client.request();
            } catch (ClassNotFoundException |IOException e) {
                new ErrorWindow().showErrorWindow("Tables Error","Tables Error","An error occurred during the tablese initialization");

            }
            count++;
        }
        listTables.getItems().addAll(list);
        listTables.getSelectionModel().clearSelection();
    }

    public void execute(ActionEvent event)  {

        if(loadButton.isSelected()) {
            try {
                client.loadDataOnServer(listTables.getSelectionModel().getSelectedItem());
                mainGui.switchToScene3();
            } catch (IOException | ClassNotFoundException e) {
                new ErrorWindow().showErrorWindow("Data Error","Data Error","An error occurred during the data loading "+"\n"+ " Table selected maybe empty");
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
                    System.out.println("er");
                }
            }

        }
    }
    public void setMainGui(Main mainGui) {
        this.mainGui = mainGui;
    }

    public void setClient(MainTest client) {
        this.client=client;
    }
}
