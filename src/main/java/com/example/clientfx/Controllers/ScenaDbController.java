package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

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

    public void setClient(MainTest client) {
        this.client=client;
    }

    public void initializeConnection(String ip, int port) {
        try {
            client = new MainTest(ip,port);
            main.setClient(client);
        } catch (IOException e) {

        }
    }

    public void configure(ActionEvent event) throws IOException {
        try {
            initializeConnection(main.getIp(),main.getPort());
            client.setDatabase(serverField.getText(), databaseField.getText(), Integer.parseInt(portField.getText()), userField.getText(), pwField.getText());
            if(!client.getAnswer()) {
                new ErrorWindow().showErrorWindow("Database Connection Error","Database Connection Error","please insert correct information");
                serverField.clear();
                databaseField.clear();
                portField.clear();
                userField.clear();
                pwField.clear();
            }else{
                main.showScena1();
            }
        }catch (Exception e) {
            //settare un alert
            new ErrorWindow().showErrorWindow("Database Connection Error","Database Connection Error","please insert correct information");
            serverField.clear();
            databaseField.clear();
            portField.clear();
            userField.clear();
            pwField.clear();

        }
    }
}
