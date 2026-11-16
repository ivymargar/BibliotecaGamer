package gamevault;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblCaracteres;
    @FXML private Label lblMensaje;
    @FXML private Button btnEntrar;

    @FXML
    public void initialize() {
        txtPassword.setPromptText("Introduce tu contrasena");

        txtPassword.textProperty().addListener((observable, anterior, nuevo) ->
                lblCaracteres.setText("Caracteres: " + nuevo.length()));
    }

    @FXML
    private void onEntrar() {
        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText();

        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Escribe un usuario y una contrasena.");
            return;
        }

        abrirPerfil(usuario);
    }

    private void abrirPerfil(String usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("perfil-view.fxml"));
            Parent root = loader.load();

            PerfilController controlador = loader.getController();
            controlador.setUsuario(usuario);

            Stage stage = (Stage) btnEntrar.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            lblMensaje.setText("Error al cargar el perfil.");
            e.printStackTrace();
        }
    }
}
