package com.example.clientfx.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Fornisce un metodo per mostrare una finestra di errore con un'icona personalizzata.
 */
public class ErrorWindow {

    /**
     * Mostra una finestra di dialogo di errore.
     *
     * @param title il titolo della finestra di errore.
     * @param header il testo dell'intestazione della finestra di errore.
     * @param content il testo del contenuto della finestra di errore.
     */
    public void showErrorWindow(String title,String Header,String Content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(Header);
        alert.setContentText(Content);
        ImageView icon = new ImageView(new Image(getClass().getResource("/com/example/clientfx/ErrorImage.png").toExternalForm()));
        icon.setFitHeight(50);
        icon.setFitWidth(50);
        alert.setGraphic(icon);
        alert.showAndWait();
    }
}
