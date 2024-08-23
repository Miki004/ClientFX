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

/**
 * Classe principale dell'applicazione ClientFX, estende {@link Application}.
 * Gestisce la finestra principale e il passaggio tra le diverse scene dell'applicazione.
 */
public class Main extends Application {
    private Stage primaryStage;
    private static String ip;
    private static int port;
    private MainTest client;

    /**
     * Punto di ingresso principale dell'applicazione JavaFX.
     * Configura la finestra principale e mostra la scena iniziale.
     *
     * @param primaryStage Il palco principale per questa applicazione.
     * @throws Exception Se si verifica un errore durante il caricamento della scena.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ClientFX Application");
        showScena1();
    }

    /**
     * Mostra la prima scena dell'applicazione.
     * Inizializza la connessione al server e imposta il controller della scena.
     *
     * @throws Exception Se si verifica un errore durante il caricamento della scena FXML.
     */
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

    /**
     * Passa alla seconda scena dell'applicazione.
     * Configura il controller della scena e gli assegna il client attuale.
     */
    public void switchToScene2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/clientfx/Scena2.fxml"));
            Parent root = loader.load();
            Scena2Controller controller = loader.getController();
            controller.setClient(client);
            controller.setMainApp(this);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Metodo principale dell'applicazione.
     * Legge l'indirizzo IP e la porta dai parametri e avvia l'applicazione JavaFX.
     *
     * @param args Gli argomenti della riga di comando, il primo argomento è l'indirizzo IP,
     *             il secondo è la porta del server.
     */
    public static void main(String[] args) {
        ip = args[0];
        port = Integer.parseInt(args[1]);

        launch(args);
    }

    /**
     * Passa alla terza scena dell'applicazione.
     * Configura il controller della scena e gli assegna il client attuale.
     */
    public void switchToScene3() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/clientfx/Scena3.fxml"));
            Parent root = loader.load();
            Scena3Controller controller = loader.getController();
            controller.setClient(client);
            controller.setMainApp(this);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
