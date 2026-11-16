package org.example;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class ControladorHojaJuegos {

    @FXML
    private TextField Nombre;
    @FXML
    private TextField Salida;
    @FXML
    private TextField Desarrollador;
    @FXML
    private TextField Plataforma;
    @FXML
    private TextField Genero;
    @FXML
    private Label MiPuntuacion;
    @FXML
    private Slider Puntuacion;
    @FXML
    private CheckBox Logros;
    @FXML
    private TextField LogrosCompletados;
    @FXML
    private TextField LogrosTotales;
    @FXML
    private CheckBox Completado;
    @FXML
    private HBox HBoxLogros;
    @FXML
    private Button Atras;
    /**
     * Metodo que se activa al iniciar el programa.
     * El metodo ayuda a configurar las partes del programa para evitar errores.
     * Si los logros que has completado superan a los logros totales añadidos,cambia el numero de logros completados al numero de totales
     * Si pones numeros negativos como logros en alguno de los dos campos el metodo los cambia y los pone a 0 automaticamente
     * Tambien ayuda con la puntuacion del slider,impidiendo que puedas poner mas de un decimal como puntuacion del juego
     */
    @FXML
    private void initialize(){
        HBoxLogros.setOpacity(0);
        MiPuntuacion.textProperty().bind(
                Bindings.format("%.1f",Puntuacion.valueProperty())
        );
        LogrosCompletados.textProperty().addListener((observable,valorAnterior,valorNuevo)->{
            try{
                int completados=Integer.parseInt(valorNuevo);
                int totales=Integer.parseInt(LogrosTotales.getText());

                if(completados>totales) {
                    LogrosCompletados.setText(String.valueOf(totales));
                }else if (completados<0){
                    LogrosCompletados.setText("0");
                }else if(totales<0){
                    LogrosTotales.setText("0");
                }
            }catch (NumberFormatException e){
                LogrosCompletados.setText("0");
            }
        });
    }

    /**
     * Metodo que se activa al darle click al checkbox "Tiene logros?"
     * Este metodo cambia la opacidad del HBox encargado de contener los TextField de logros a 1 en caso de hacer click en el checkbox
     * En caso contrario,deja la opacidad en 0
     */
    @FXML
    private void TieneLogros(){
        HBoxLogros.opacityProperty().bind(
                Bindings.when(Logros.selectedProperty()).
                        then(1).
                        otherwise(0
                )
        );
    }
     @FXML
    private void IrAtras() throws IOException {
        App.setRoot("bibliotecaGamer");
    }
}
