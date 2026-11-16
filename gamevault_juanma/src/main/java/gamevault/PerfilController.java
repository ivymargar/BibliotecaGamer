package gamevault;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PerfilController {
    private static final String CLASE_OSCURO = "oscuro";

    @FXML private VBox raiz;
    @FXML private Label lblBienvenida;
    @FXML private ToggleButton tglModoOscuro;
    @FXML private ToggleButton tglFavoritos;
    @FXML private Label lblResumen;
    @FXML private Button btnCerrarSesion;

    @FXML
    public void initialize() {
        tglModoOscuro.setSelected(false);
        tglFavoritos.setSelected(false);
        actualizarTextos();
    }

    public void setUsuario(String usuario) {
        lblBienvenida.setText("Bienvenido, " + usuario);
    }

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

    @FXML
    private void onFavoritos() {
        actualizarTextos();
    }

    @FXML
    private void onCerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarTextos() {
        tglModoOscuro.setText(tglModoOscuro.isSelected() ? "ON" : "OFF");
        tglFavoritos.setText(tglFavoritos.isSelected() ? "ON" : "OFF");

        lblResumen.setText("Modo oscuro: " + tglModoOscuro.getText()
                + "  |  Solo favoritos: " + tglFavoritos.getText());
    }
}
