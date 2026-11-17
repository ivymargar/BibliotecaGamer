module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.modelo;
    opens org.example.modelo to javafx.fxml;
    exports org.example.controlador;
    opens org.example.controlador to javafx.fxml;
}
