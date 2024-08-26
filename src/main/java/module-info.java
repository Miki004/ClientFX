module com.example.clientfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.example.clientfx to javafx.fxml;
    exports com.example.clientfx;
    exports com.example.clientfx.Controllers;
    opens com.example.clientfx.Controllers to javafx.fxml;
}