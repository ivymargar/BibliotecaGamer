package org.example.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import org.example.App;

import java.io.IOException;

/**
 * Controlador de la pantalla de perfil de Biblioteca Gamer.
 *
 * Gestiona la información del usuario y las preferencias
 * disponibles en la pantalla de perfil.
 *
 * Código desarrollado originalmente por JuanMa
 * y adaptado al proyecto común.
 */
public class PerfilController {

    /**
     * Clase CSS utilizada para activar el modo oscuro.
     */
    private static final String CLASE_OSCURO = "oscuro";

    /**
     * Contenedor principal de la pantalla de perfil.
     */
    @FXML
    private VBox raiz;

    /**
     * Etiqueta que muestra el nombre del usuario.
     */
    @FXML
    private Label lblBienvenida;

    /**
     * Botón utilizado para activar o desactivar
     * el modo oscuro.
     */
    @FXML
    private ToggleButton tglModoOscuro;

    /**
     * Botón utilizado para activar o desactivar
     * la opción de mostrar únicamente favoritos.
     */
    @FXML
    private ToggleButton tglFavoritos;

    /**
     * Etiqueta que muestra un resumen de las
     * preferencias seleccionadas.
     */
    @FXML
    private Label lblResumen;

    /**
     * Botón utilizado para cerrar la sesión actual.
     */
    @FXML
    private Button btnCerrarSesion;

    /**
     * Inicializa las preferencias de la pantalla de perfil.
     */
    @FXML
    public void initialize() {

        tglModoOscuro.setSelected(false);
        tglFavoritos.setSelected(false);

        actualizarTextos();
    }

    /**
     * Muestra en la pantalla de perfil el nombre
     * del usuario que ha iniciado sesión.
     *
     * @param usuario nombre del usuario
     */
    public void setUsuario(String usuario) {

        lblBienvenida.setText(
                "Bienvenido, " + usuario
        );
    }

    /**
     * Activa o desactiva el modo oscuro de la
     * pantalla de perfil.
     */
    @FXML
    private void onModoOscuro() {

        if (tglModoOscuro.isSelected()) {

            if (!raiz.getStyleClass().contains(CLASE_OSCURO)) {

                raiz.getStyleClass().add(CLASE_OSCURO);
            }

        } else {

            raiz.getStyleClass().remove(CLASE_OSCURO);
        }

        actualizarTextos();
    }

    /**
     * Actualiza el estado de la opción utilizada
     * para mostrar únicamente videojuegos favoritos.
     */
    @FXML
    private void onFavoritos() {

        actualizarTextos();
    }

    /**
     * Cierra la sesión actual y vuelve a mostrar
     * la pantalla de acceso de Biblioteca Gamer.
     */
    @FXML
    private void onCerrarSesion() {

        try {

            /*
             * Sustituimos la pantalla de Perfil
             * por la pantalla de Login.
             */
            App.setRoot("login-view");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * Actualiza los textos de los ToggleButton
     * y el resumen de preferencias.
     */
    private void actualizarTextos() {

        tglModoOscuro.setText(
                tglModoOscuro.isSelected() ? "ON" : "OFF"
        );

        tglFavoritos.setText(
                tglFavoritos.isSelected() ? "ON" : "OFF"
        );

        lblResumen.setText(
                "Modo oscuro: " + tglModoOscuro.getText()
                        + "  |  Solo favoritos: "
                        + tglFavoritos.getText()
        );
    }
}