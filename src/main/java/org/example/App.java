package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación Biblioteca Gamer.
 *
 * Se encarga de iniciar la aplicación JavaFX y cargar
 * la vista principal bibliotecaGamer.fxml.
 *
 * @author Leo Martínez, Dani Merino, Iván y JuanMa
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Inicializa y muestra la ventana principal de la aplicación.
     *
     * @param stage ventana principal de JavaFX
     * @throws IOException si se produce un error al cargar el archivo FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("bibliotecaGamer"), 900, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Permite sustituir la vista actualmente mostrada
     * por otro archivo FXML.
     *
     * @param fxml nombre del archivo FXML sin extensión
     * @throws IOException si no se puede cargar el archivo
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga un archivo FXML desde los recursos del proyecto.
     *
     * @param fxml nombre del archivo FXML sin extensión
     * @return nodo raíz contenido en el archivo FXML
     * @throws IOException si se produce un error durante la carga
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos recibidos al iniciar el programa
     */
    public static void main(String[] args) {
        launch();
    }

}