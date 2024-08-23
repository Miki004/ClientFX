package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller per la gestione della terza scena dell'applicazione.
 * Questo controller gestisce l'estrazione di dati dendrogrammatici
 * dal server basandosi sulla profondità specificata e sul tipo di distanza scelto.
 */
public class Scena3Controller implements Initializable {

    public TextField spaceDepth;
    public ChoiceBox<String> choiceDistance;
    public TextArea mineArea;
    public TextField spaceSave;
    public Button mineButton;
    public Label nameFileLabel;
    private MainTest client;
    private Main mainApp;
    private String[] distanceType = {"Single-Link","Average-Link"};

    public void setClient(MainTest client) {
        this.client=client;
        client.setScene(this);
    }

    /**
     * Imposta l'istanza dell'app principale associata a questo controller.
     *
     * @param mainApp L'istanza di {@link Main}.
     */
    public void setMainApp(Main mainApp) { this.mainApp = mainApp; }

    /**
     * Imposta l'istanza del client associato a questo controller.
     *
     * @param client L'istanza di {@link MainTest}.
     */
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

    /**
     * Ottiene e imposta il tipo di distanza scelto dall'utente.
     *
     * @param event L'evento di azione associato all'invocazione del metodo.
     */
    public void getDistance(ActionEvent event) {
        if(Objects.equals(choiceDistance.getValue(), "Single-Link")) {
            client.setType(1);
        }else{
            client.setType(2);
        }
    }

    /**
     * Inizializza il controller, popolando il ChoiceBox con i tipi di distanza disponibili
     * e configurando l'azione da eseguire alla selezione di un tipo di distanza.
     *
     * @param url L'URL utilizzato per risolvere il percorso del file FXML.
     * @param resourceBundle Le risorse utilizzate per localizzare il file FXML.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceDistance.getItems().addAll(distanceType);
        choiceDistance.setOnAction(this::getDistance);
    }

    /**
     * Torna alla scena 1 dell'applicazione.
     * Viene chiamato quando l'utente preme il pulsante "Back to Scene 1".
     *
     * @param event L'evento di azione associato al pulsante.
     */
    public void goBackToScene1(ActionEvent event) {
        try {
            mainApp.showScena1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
