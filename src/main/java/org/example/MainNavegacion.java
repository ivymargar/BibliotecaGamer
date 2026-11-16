package navegacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal para probar la parte de navegación de GAME VAULT.
 *
 * @author Iván Márquez Sánchez
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Carga el FXML y muestra la ventana.
     *
     * @param stage ventana principal
     * @throws Exception si no se puede cargar el FXML
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Navegacion.fxml"));
        Scene scene = new Scene(loader.load());

        NavegacionController controlador = loader.getController();
        controlador.setHostServices(getHostServices());

        stage.setTitle("GAME VAULT - Navegación");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}
