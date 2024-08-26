package com.example.clientfx.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ErrorWindow {
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
