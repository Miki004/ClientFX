package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller per la gestione della prima scena dell'applicazione.
 * Gestisce la connessione al server, l'invio di dati e il passaggio tra diverse scene.
 */
public class Scena1Controller {


    @FXML
    public TextField tableSpace;
    private Main mainApp;
    @FXML
    private TextField ipField;
    @FXML
    private TextField portField;
    @FXML
    private RadioButton loadFromFileButton;
    @FXML
    private RadioButton mineFromDbButton;
    @FXML
    private Button MyButton;
    @FXML
    private TextArea outputArea;
    private MainTest client;



    /**
     * Riceve il nome della tabella dallo spazio di input e lo invia al server.
     *
     * @param e L'evento di azione associato all'invocazione del metodo.
     */
    public void riceviTabella(ActionEvent e) {
            client.setNameFile(tableSpace.getText());
            try {
               client.loadDataOnServer();
               outputArea.appendText("Sent data: " + "\n");
            } catch (IOException | ClassNotFoundException ev) {
                outputArea.appendText("Failed to send data: " + ev.getMessage() + "\n");
                outputArea.appendText("Please enter an existing table" + "\n");
                tableSpace.clear();
            }
    }

    /**
     * Scrive una parola nell'area di output.
     *
     * @param word La parola da scrivere.
     */
    public void writeOnArea(String word) {
        outputArea.appendText(word + "\n");
    }

    /**
     * Restituisce l'istanza del client associato a questo controller.
     *
     * @return L'istanza di {@link MainTest}.
     */
    public MainTest getClient() {
        return client;
    }

    /**
     * Inizializza la connessione al server usando l'IP e la porta specificati.
     *
     * @param ip L'indirizzo IP del server.
     * @param port La porta del server.
     */
    public void initializeConnection(String ip, int port) {
        ipField.setText(ip);
        portField.setText(String.valueOf(port));
        try {
            outputArea.appendText(ip + port);
            client = new MainTest(ip, port);
            client.setMainScene(this);
            outputArea.appendText("Connected to server at " + ip + ":" + port + "\n");
        } catch (IOException e) {
            outputArea.appendText("Failed to connect: " + e.getMessage() + "\n");
        }
    }

    /**
     * Imposta l'istanza principale dell'applicazione.
     *
     * @param mainApp L'istanza di {@link Main}.
     */
    @FXML
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Esegue l'opzione selezionata, cambiando scena a seconda della selezione.
     * Seleziona tra il caricamento dei dati da file o l'estrazione dal database.
     */
    @FXML
    private void executeSelectedOption() {
        if (client == null) {
            outputArea.appendText("You must connect to the server first.\n");
            return;
        }
        if (loadFromFileButton.isSelected()) {
            mainApp.switchToScene2();
        } else if (mineFromDbButton.isSelected()) {
            mainApp.switchToScene3();
        }
    }

    public void requestNameTable() {
        tableSpace.clear();
        String nameTable;
        do {
             nameTable=tableSpace.getText();
            if (nameTable != null) {
                client.setNameTable(nameTable);
            }
        }while (nameTable==null);

    }
}
