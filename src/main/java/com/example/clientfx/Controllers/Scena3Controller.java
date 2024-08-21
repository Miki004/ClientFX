package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Scena3Controller implements Initializable {

    public TextField spaceDepth;
    public ChoiceBox<String> choiceDistance;
    public TextArea mineArea;
    public TextField spaceSave;
    public Button mineButton;
    public Label nameFileLabel;
    private MainTest client;
    private String[] distanceType = {"Single-Link","Average-Link"};

    public void setClient(MainTest client) {
        this.client=client;
        client.setScene(this);
    }

    public void execute(ActionEvent event) {
        int depth=5;
        try {
            depth=Integer.parseInt(spaceDepth.getText());
        }catch (NumberFormatException e) {
            mineArea.appendText("please enter a number ");
            spaceDepth.clear();
            depth=Integer.parseInt(spaceDepth.getText());
        }
        client.setDepth(depth);
        client.setNameFile(spaceSave.getText());

        try {
            mineArea.clear();
            client.mineDedrogramOnServer();
            mineArea.appendText(client.getOutput());
        } catch (IOException | ClassNotFoundException e) {
            mineArea.appendText("Mining error");
        }


    }

    public void getDistance(ActionEvent event) {
        if(Objects.equals(choiceDistance.getValue(), "Single-Link")) {
            client.setType(1);
        }else{
            client.setType(2);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceDistance.getItems().addAll(distanceType);
        choiceDistance.setOnAction(this::getDistance);
    }
}
