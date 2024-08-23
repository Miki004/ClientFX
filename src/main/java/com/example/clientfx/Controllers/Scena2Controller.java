package com.example.clientfx.Controllers;

import com.example.clientfx.Main;
import com.example.clientfx.Client.MainTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.io.IOException;
/**
 * Controller per la gestione della seconda scena dell'applicazione.
 * Questo controller è responsabile del caricamento dei dati da un file
 * e della visualizzazione delle informazioni dendrogrammatiche nell'area di testo.
 */
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
    private MainTest client;
    private Main mainApp;

    /**
     * Restituisce il nome del file attualmente impostato.
     *
     * @return Il nome del file.
     */
    public String getNameFile() {
       return nameFile;
    }

    /**
     * Imposta il client associato a questo controller.
     *
     * @param client L'istanza di {@link MainTest}.
     */
    public void setClient(MainTest client) {
        this.client =client;
    }

    /**
     * Imposta l'istanza dell'app principale associata a questo controller.
     *
     * @param mainApp L'istanza di {@link Main}.
     */
    public void setMainApp(Main mainApp) { this.mainApp = mainApp; }

    /**
     * Esegue l'azione associata al pulsante selezionato.
     * Se l'opzione di caricamento da file è selezionata, viene pulita l'area di testo
     * e vengono caricati i dati dendrogrammatici dal file specificato.
     *
     * @param e L'evento di azione associato all'invocazione del metodo.
     */
    public void execute(ActionEvent e ) {
        if(loadFromFileButton.isSelected()) {
            clusterArea.clear();
            client.setNameFile(fileSpace.getText());
            setDendrogramData();
        }
    }

    /**
     * Carica i dati dendrogrammatici dal file sul server e li visualizza nell'area di testo.
     * In caso di errore durante il caricamento, viene visualizzato un messaggio di errore.
     */
    public void setDendrogramData()  {
        try {
            client.loadDedrogramFromFileOnServer();
            clusterArea.appendText(client.getOutput());
        }catch (IOException | ClassNotFoundException ec) {
            clusterArea.appendText("Errore nel caricamento");
        }

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
