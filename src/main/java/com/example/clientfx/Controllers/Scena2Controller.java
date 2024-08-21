package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Scena2Controller {

    @FXML
    public Label fileLabel;
    @FXML
    public RadioButton loadFromFileButton;
    @FXML
    private TextArea clusterArea;
    @FXML
    private TextField fileSpace;

    private String nameFile;
    private  MainTest client;

    public String getNameFile() {
       return nameFile;
    }

    public void setClient(MainTest client) {
        this.client =client;
    }

    public void execute(ActionEvent e ) {
        if(loadFromFileButton.isSelected()) {
            clusterArea.clear();
            client.setNameFile(fileSpace.getText());
            setDendrogramData();
        }
    }

    public void setDendrogramData()  {
        try {
            client.loadDedrogramFromFileOnServer();
            clusterArea.appendText(client.getOutput());
        }catch (IOException | ClassNotFoundException ec) {
            clusterArea.appendText("Errore nel caricamento");
        }

    }

}
