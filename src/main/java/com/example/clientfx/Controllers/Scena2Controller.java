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

/**
 * Controller per la scena che gestisce la costruzione e il salvataggio dei cluster.
 * Permette all'utente di configurare i parametri di clustering e di salvare i risultati.
 */
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

    /**
     * Costruisce il clustering utilizzando i parametri specificati e visualizza l'output.
     * Mostra le opzioni di salvataggio se la costruzione ha successo.
     *
     * @param event l'evento di clic sul pulsante di costruzione.
     * @throws Exception se si verifica un errore durante la costruzione del clustering.
     */
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

    /**
     * Inizializza il controller e carica le opzioni di clustering nella scelta.
     *
     * @param url l'URL relativo al file FXML.
     * @param resourceBundle il ResourceBundle per la localizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clusteringOptions.getItems().addAll("Single-Link-Distance", "Average-Link-Distance");

    }

    /**
     * Imposta il riferimento al client per comunicare con il server.
     *
     * @param client il client utilizzato per le comunicazioni con il server.
     */
    public void setClient(MainTest client) {
        this.client=client;
    }

    /**
     * Salva il clustering utilizzando il nome del file specificato.
     *
     * @param event l'evento di clic sul pulsante di salvataggio.
     */
    public void saveCluster(ActionEvent event) {
            try {
                nameFile=saveField.getText();
                client.save();
                main.showScena1();
            }catch (Exception e) {
                new ErrorWindow().showErrorWindow("Saving Error","Saving Error","An error occurs during the clustering saving");
            }
    }

    /**
     * Restituisce il nome del file per il salvataggio del clustering.
     *
     * @return il nome del file.
     */
    public String getNameFile() {
        return nameFile;
    }

    /**
     * Mostra i controlli per il salvataggio del clustering.
     *
     * @param event l'evento di clic sul pulsante per mostrare le opzioni di salvataggio.
     */
    public void showSave(ActionEvent event) {
            labelSave.setVisible(true);
            saveField.setVisible(true);
            saveButton.setVisible(true);
    }

    /**
     * Imposta il riferimento alla GUI principale dell'applicazione.
     *
     * @param main il riferimento alla GUI principale.
     */
    public void setMain(Main main) {
        this.main=main;
    }

    /**
     * Torna alla scena 1.
     *
     * @param event l'evento di clic sul pulsante per tornare indietro.
     */
    public void goBack(ActionEvent event) {
        try {
            main.showScena1();
        } catch (Exception e) {
            new ErrorWindow().showErrorWindow("Go back Error","Go back Error","An error occurs during the turning back ");
        }
    }
}
