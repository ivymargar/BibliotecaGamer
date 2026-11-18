package org.example.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import org.example.App;

/**
 * Controlador de la pantalla de acceso a GAME VAULT.
 *
 * Gestiona la introducción del usuario y la contraseña,
 * valida que los campos no estén vacíos y permite acceder
 * a la pantalla principal de Biblioteca Gamer.
 *
 * Código desarrollado originalmente por JuanMa
 * y adaptado al proyecto común.
 */
public class LoginController {

    /**
     * Campo utilizado para introducir el nombre de usuario.
     */
    @FXML
    private TextField txtUsuario;

    /**
     * Campo protegido utilizado para introducir la contraseña.
     */
    @FXML
    private PasswordField txtPassword;

    /**
     * Etiqueta que muestra la cantidad de caracteres
     * introducidos en la contraseña.
     */
    @FXML
    private Label lblCaracteres;

    /**
     * Etiqueta utilizada para mostrar mensajes de validación.
     */
    @FXML
    private Label lblMensaje;

    /**
     * Botón utilizado para entrar en la aplicación.
     */
    @FXML
    private Button btnEntrar;

    /**
     * Inicializa los componentes de la pantalla de acceso.
     *
     * Configura el texto orientativo del PasswordField y
     * actualiza automáticamente el contador de caracteres
     * mientras el usuario escribe la contraseña.
     */
    @FXML
    public void initialize() {

        txtPassword.setPromptText("Introduce tu contraseña");

        txtPassword.textProperty().addListener(
                (observable, anterior, nuevo) -> {

                    lblCaracteres.setText(
                            "Caracteres: " + nuevo.length()
                    );
                }
        );
    }

    /**
     * Comprueba los datos introducidos por el usuario.
     *
     * Si alguno de los campos está vacío, muestra un mensaje.
     * Si ambos contienen información, guarda el nombre del
     * usuario y accede a Biblioteca Gamer.
     */
    @FXML
    private void onEntrar() {

        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText();

        /*
         * Comprobamos que ambos campos tengan contenido.
         */
        if (usuario.isEmpty() || password.isEmpty()) {

            lblMensaje.setText(
                    "Escribe un usuario y una contraseña."
            );

            return;
        }

        try {

            /*
             * Guardamos el nombre del usuario para poder
             * utilizarlo posteriormente en su perfil.
             */
            App.setUsuarioActual(usuario);

            /*
             * Cambiamos la pantalla de Login por la pantalla
             * principal de Biblioteca Gamer y adaptamos
             * el tamaño de la ventana a su diseño.
             */
            App.mostrarBiblioteca();

        } catch (IOException e) {

            /*
             * Mostramos un mensaje si se produce algún
             * problema al cargar la pantalla principal.
             */
            lblMensaje.setText(
                    "Error al cargar la Biblioteca Gamer."
            );

            e.printStackTrace();
        }
    }
}