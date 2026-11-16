module gamevault {
    requires javafx.controls;
    requires javafx.fxml;

    opens gamevault to javafx.fxml;
    exports gamevault;
}
