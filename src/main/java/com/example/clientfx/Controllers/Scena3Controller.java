package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller per la scena che gestisce il caricamento e la visualizzazione del clustering.
 * Permette all'utente di caricare un clustering da un file e di visualizzare i risultati.
 */
public class Scena3Controller {
    @FXML
    private Button loadButton;
    @FXML
    private TextField loadField;
    @FXML
    private TextArea loadArea;
    @FXML
    private Button backButton;

    private MainTest client;
    private Main main;

    /**
     * Imposta il riferimento al client per comunicare con il server.
     *
     * @param client il client utilizzato per le comunicazioni con il server.
     */
    public void setClient(MainTest client) {
        this.client=client;
    }

    /**
     * Carica il clustering dal file specificato e visualizza i risultati nell'area di testo.
     *
     * @param event l'evento di clic sul pulsante di caricamento.
     */
    public void loadClustering(ActionEvent event) {
        try {
            loadArea.clear();
            client.loadDedrogramFromFileOnServer(loadField.getText());
            loadArea.appendText(client.getOutput());

        } catch (IOException | ClassNotFoundException e) {
            new ErrorWindow().showErrorWindow("Loading Error","Loading Error","An error occurs during the clustering uploading");
        }
        backButton.setVisible(true);
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
     * Gestisce l'evento di azione per tornare alla scena precedente.
     *
     * Questo metodo viene attivato quando si verifica un'azione da parte dell'utente, come il clic su un pulsante.
     * Tenta di mostrare la scena precedente invocando il metodo `showScena1()` sull'oggetto principale dell'applicazione.
     * Se si verifica un'eccezione durante questo processo, viene catturata e viene visualizzato un messaggio di errore
     * in una nuova finestra di errore.
     *
     *
     * @param event L'ActionEvent che ha attivato questo metodo, tipicamente associato a un'azione dell'utente.
     */
    public void backScene(ActionEvent event) {
        try {
            main.showScena1();
        }catch(Exception e) {
            new ErrorWindow().showErrorWindow("Error","Error","An error occurs during the turning back");
        }

    }
}

