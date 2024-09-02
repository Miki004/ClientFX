package com.example.clientfx.Controllers;

import com.example.clientfx.Client.MainTest;
import com.example.clientfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.util.List;

/**
 * Controller per la scena che gestisce la selezione delle tabelle e l'operazione da eseguire.
 * Permette all'utente di caricare dati o eseguire clustering.
 */
public class Scena1Controller {

    @FXML
    private RadioButton loadButton;
    @FXML
    private RadioButton clusterButton;
    private MainTest client;
    @FXML
    private ListView<String> listTables;
    private Main mainGui;
    private static int count=0;
    private static List<String> list;

    /**
     * Inizializza la lista delle tabelle se non è già stata inizializzata.
     * Recupera la lista delle tabelle dal server e la visualizza nella lista delle tabelle.
     */
    public void initializeTables() {
        if (count==0) {
            try {
                list=client.request();
            } catch (ClassNotFoundException |IOException e) {
                new ErrorWindow().showErrorWindow("Tables Error","Tables Error","An error occurred during the tablese initialization");

            }
            count++;
        }
        listTables.getItems().addAll(list);
        listTables.getSelectionModel().clearSelection();
    }

    /**
     * Gestisce l'azione del pulsante per eseguire l'operazione selezionata (caricamento dati o clustering).
     *
     * @param event l'evento di clic sul pulsante.
     */
    public void execute(ActionEvent event)  {

        if(loadButton.isSelected()) {
            try {
                client.loadDataOnServer(listTables.getSelectionModel().getSelectedItem());
                mainGui.switchToScene3();
            } catch (IOException | ClassNotFoundException e) {
                new ErrorWindow().showErrorWindow("Data Error","Data Error","An error occurred during the data loading "+"\n"+ " Table selected maybe empty");
                try {
                    mainGui.showScena1();
                } catch (Exception ex) {
                    System.out.println("err");
                }
            }
        }else if (clusterButton.isSelected()) {
            try {
                client.loadDataOnServer(listTables.getSelectionModel().getSelectedItem());
                mainGui.switchToScene2();
            } catch (IOException | ClassNotFoundException e) {
                new ErrorWindow().showErrorWindow("Data Error","Data Error","An error occurred during the data loading"+"\n"+ "Table selected maybe empty");
                try {
                    mainGui.showScena1();
                } catch (Exception ex) {
                    System.out.println("er");
                }
            }

        }
    }

    /**
     * Imposta il riferimento alla GUI principale dell'applicazione.
     *
     * @param mainGui il riferimento alla GUI principale.
     */
    public void setMainGui(Main mainGui) {
        this.mainGui = mainGui;
    }

    /**
     * Imposta il riferimento al client per comunicare con il server.
     *
     * @param client il client utilizzato per le comunicazioni con il server.
     */
    public void setClient(MainTest client) {
        this.client=client;
    }
}
