package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class ScenaDbController {
    public TextField serverField;
    public TextField databaseField;
    public TextField portField;
    public TextField userField;
    public PasswordField pwField;
    private Main main;
    private MainTest client;

    public void setMain(Main main) {
        this.main= main;
    }

    public void initializeConnection(String ip, int port) {
        try {
            System.out.println(ip + port);
            client = new MainTest(ip,port);
            main.setClient(client);
        } catch (IOException e) {
            System.out.println("client non creato");
        }
    }

    public void configure(ActionEvent event) throws Exception {
        try {
            String server = serverField.getText();
            String database = databaseField.getText();
            int port = Integer.parseInt(portField.getText());
            String user = userField.getText();
            String pw = pwField.getText();
            client.setDatabase(server, database, port, user, pw);
            boolean isConnected = client.getAnswer();
            System.out.println("Server response: " + isConnected);
            if (!isConnected) {
                new ErrorWindow().showErrorWindow("Database Connection Error","Database Connection Error", "Please insert correct information");
                clearFields();
                main.showScenaSetDB();
            } else {
                main.showScena1();
            }

        } catch (NumberFormatException e) {
            // Gestione dell'errore nel caso il numero di porta non sia valido
            new ErrorWindow().showErrorWindow("Invalid Port", "Invalid Port Number", "Please enter a valid port number.");
            clearFields();
        } catch (IOException | ClassNotFoundException e) {
            // Gestione dell'errore durante la comunicazione con il server
            new ErrorWindow().showErrorWindow("Connection Error", "Error during communication with server", e.getMessage());
            clearFields();
            main.showScenaSetDB();  // Ritorna alla scena di configurazione
        }
    }

    private void clearFields() {
        serverField.clear();
        databaseField.clear();
        portField.clear();
        userField.clear();
        pwField.clear();
    }


}
