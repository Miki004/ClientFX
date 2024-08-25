package com.example.clientfx.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ErrorWindow {
    public void showErrorWindow() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Connection Error");
        alert.setHeaderText("Connection Error");
        alert.setContentText("An error has occurred during the connection with the server");
        ImageView icon = new ImageView(new Image(getClass().getResource("/com/example/clientfx/ErrorImage.png").toExternalForm()));
        icon.setFitHeight(50);
        icon.setFitWidth(50);
        alert.setGraphic(icon);
        alert.showAndWait();
    }
}
