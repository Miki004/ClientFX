package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scena2Controller implements Initializable {
    @FXML
    private RadioButton yesButton;
    @FXML
    private RadioButton noButton;
    @FXML
    private TextField depthField;
    @FXML
    private TextArea outputArea;
    @FXML
    private TextField saveField;
    @FXML
    private Button saveButton;
    @FXML
    private Button buildButton;
    @FXML
    private Label labelSave;
    @FXML
    private Label labelOption;
    @FXML
    private ChoiceBox<String> clusteringOptions;
    private MainTest client;
    private String nameFile;
    private Main main;

    public void buildCluster(ActionEvent event) throws Exception {
        try {
            client.setDepth(Integer.parseInt(depthField.getText()));
            client.setOption(clusteringOptions.getValue());
            client.mineDedrogramOnServer();
            outputArea.appendText(client.getOutput());
            labelOption.setVisible(true);
            yesButton.setVisible(true);
            noButton.setVisible(true);
        } catch (IOException | ClassNotFoundException | NumberFormatException e) {
            new ErrorWindow().showErrorWindow("Mining Error","Mining Error","An error occurs during the clustering building");
            depthField.clear();
            main.showScena1();
        }catch(NullPointerException e) {
            new ErrorWindow().showErrorWindow("Type Error","Type Error","Please select a type of clustering");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clusteringOptions.getItems().addAll("Single-Link-Distance", "Average-Link-Distance");

    }

    public void setClient(MainTest client) {
        this.client=client;
    }

    public void saveCluster(ActionEvent event) {
            try {
                nameFile=saveField.getText();
                client.save();
                main.showScena1();
            }catch (Exception e) {
                new ErrorWindow().showErrorWindow("Saving Error","Saving Error","An error occurs during the clustering saving");
            }
    }

    public String getNameFile() {
        return nameFile;
    }

    public void showSave(ActionEvent event) {
            labelSave.setVisible(true);
            saveField.setVisible(true);
            saveButton.setVisible(true);
    }

    public void setMain(Main main) {
        this.main=main;
    }

    public void goBack(ActionEvent event) {
        try {
            main.showScena1();
        } catch (Exception e) {
            new ErrorWindow().showErrorWindow("Go back Error","Go back Error","An error occurs during the turning back ");
        }
    }
}
