package com.example.clientfx.Controllers;

import com.example.clientfx.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Controller per la scena di connessione iniziale dell'applicazione client.
 * Gestisce l'inserimento dell'indirizzo IP e del numero di porta e verifica la connessione al server.
 */
public class Scena0Controller {
    @FXML
    private TextField ipField;
    @FXML
    private TextField portField;
    private Main mainGui;

    /**
     * Gestisce l'evento di clic sul pulsante di avvio per connettersi al server.
     *
     * @param event l'evento di clic sul pulsante.
     * @throws Exception se si verifica un errore durante il tentativo di connessione.
     */
    public void start(ActionEvent event) throws Exception {

        try {
            String ip=ipField.getText();
            int port = Integer.parseInt(portField.getText());
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 100);
            if (socket.isConnected()) {
                mainGui.setIp(ip);
                mainGui.setPort(port);
                socket.close();
                mainGui.showScenaSetDB();
            } else {
                new ErrorWindow().showErrorWindow("CONNECTION ERROR", "CONNECTION ERROR", "Please check your parameters");
                ipField.clear();
                portField.clear();
            }
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            new ErrorWindow().showErrorWindow("CONNECTION ERROR", "CONNECTION ERROR", "Please check your parameters");
            ipField.clear();
            portField.clear();
        }


    }

    /**
     * Imposta il riferimento alla GUI principale.
     *
     * @param mainGui il riferimento alla GUI principale.
     */
    public void setMainGui(Main mainGui) {
        this.mainGui = mainGui;
    }
}
