package com.example.clientfx;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Controllers.Scena1Controller;
import com.example.clientfx.Controllers.Scena2Controller;
import com.example.clientfx.Controllers.Scena3Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private static String ip;
    private static int port;
    private MainTest client;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ClientFX Application");
        showScena1();
    }

    public void showScena1() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/clientfx/Scena1.fxml"));
        Parent root = loader.load();
        Scena1Controller controller = loader.getController();
        controller.initializeConnection(ip,port);
        controller.setMainApp(this);
        client=controller.getClient();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void switchToScene2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/clientfx/Scena2.fxml"));
            Parent root = loader.load();
            Scena2Controller controller = loader.getController();
            controller.setClient(client);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        ip = args[0];
        port = Integer.parseInt(args[1]);

        launch(args);
    }

    public void switchToScene3() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/clientfx/Scena3.fxml"));
            Parent root = loader.load();
            Scena3Controller controller = loader.getController();
            controller.setClient(client);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
