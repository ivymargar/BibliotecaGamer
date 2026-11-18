package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación GAME VAULT.
 *
 * Se encarga de iniciar la aplicación JavaFX y cargar
 * inicialmente la pantalla de acceso.
 *
 * También permite cambiar entre las diferentes vistas
 * de la aplicación.
 *
 * @author Leo Martínez, Dani Merino, Iván y Juan
 */
public class App extends Application {

    /**
     * Escena principal utilizada durante toda la aplicación.
     */
    private static Scene scene;

    /**
     * Ventana principal de la aplicación.
     * Se conserva para poder adaptar su tamaño al cambiar de pantalla.
     */
    private static Stage stage;

    /**
     * Nombre del usuario que ha iniciado sesión.
     */
    private static String usuarioActual;

    /**
     * Inicializa y muestra la ventana principal de la aplicación.
     *
     * Al iniciar GAME VAULT se muestra primero la pantalla
     * de Login.
     *
     * @param primaryStage ventana principal de JavaFX
     * @throws IOException si se produce un error al cargar el FXML
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;

        scene = new Scene(
                loadFXML("login-view"),
                380,
                420
        );

        stage.setTitle("BIBLIOTECA GAMER");
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
    public static void setRoot(String fxml) throws IOException {

        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Permite sustituir la vista actual utilizando
     * un nodo raíz que ya ha sido cargado.
     *
     * @param root nuevo nodo raíz de la escena
     */
    public static void setRoot(Parent root) {

        scene.setRoot(root);
    }

    /**
     * Muestra la pantalla principal de Biblioteca Gamer
     * y adapta el tamaño de la ventana a su diseño.
     *
     * @throws IOException si no se puede cargar el archivo FXML
     */
    public static void mostrarBiblioteca() throws IOException {

        scene.setRoot(loadFXML("bibliotecaGamer"));

        stage.setWidth(900);
        stage.setHeight(600);

        stage.centerOnScreen();
    }

    /**
     * Guarda el nombre del usuario que ha iniciado sesión.
     *
     * @param usuario nombre introducido en la pantalla de Login
     */
    public static void setUsuarioActual(String usuario) {

        usuarioActual = usuario;
    }

    /**
     * Devuelve el nombre del usuario que ha iniciado sesión.
     *
     * @return nombre del usuario actual
     */
    public static String getUsuarioActual() {

        return usuarioActual;
    }

    /**
     * Carga un archivo FXML desde los recursos del proyecto.
     *
     * @param fxml nombre del archivo FXML sin extensión
     * @return nodo raíz contenido en el archivo FXML
     * @throws IOException si se produce un error durante la carga
     */
    private static Parent loadFXML(String fxml) throws IOException {

        FXMLLoader fxmlLoader =
                new FXMLLoader(App.class.getResource(fxml + ".fxml"));

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
