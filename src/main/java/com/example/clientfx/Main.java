package com.example.clientfx;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Controllers.*;
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
     * Imposta l'indirizzo IP del server.
     *
     * @param ip l'indirizzo IP del server.
     */
    public void setIp(String ip) {
        Main.ip = ip;
    }

    /**
     * Imposta il numero di porta del server.
     *
     * @param port il numero di porta del server.
     */
    public void setPort(int port) {
        Main.port = port;
    }

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
        this.primaryStage.setTitle("H-CLUS-CLIENT");
        showScena0();
    }

    /**
     * Mostra la scena 0 (la scena di avvio) dell'applicazione.
     *
     * @throws IOException Se si verifica un errore durante il caricamento della scena FXML.
     */
    public void showScena0() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/clientfx/Scena0.fxml"));
        Parent root = loader.load();
        Scena0Controller controller = loader.getController();
        controller.setMainGui(this);
        primaryStage.setScene(new Scene(root,400,400));
        primaryStage.setResizable(false);
        primaryStage.show();
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
        Scena1Controller controller= loader.getController();
        controller.setMainGui(this);
        controller.setClient(client);
        controller.initializeTables();
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
            controller.setClient(this.client);
            controller.setMain(this);
            this.client.setControllerScena2(controller);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());

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
            controller.setMain(this);
            controller.setClient(this.client);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
           System.out.println(e.getMessage());
        }
    }

    /**
     * Imposta l'oggetto client per comunicare con il server.
     *
     * @param client l'oggetto client.
     */
    public void setClient(MainTest client) {
        this.client=client;
    }

    /**
     * Mostra la scena di configurazione del database.
     *
     * @throws IOException Se si verifica un errore durante il caricamento della scena FXML.
     */
    public void showScenaSetDB() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/clientfx/ScenaDb.fxml"));
        Parent root = loader.load();
        ScenaDbController controller = loader.getController();
        controller.setMain(this);
        controller.initializeConnection(ip,port);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
