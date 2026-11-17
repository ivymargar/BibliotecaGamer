package org.example;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    private Button Guardar;

    private BibliotecaJuegos biblio;
    private String nombreJuego;
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
        Stage stage = (Stage) Atras.getScene().getWindow();

        stage.close();
    }

    /**
     *
     * @throws IOException
     *  Metodo encargado de guardar y editar los juegos del array de bibliotecajuegos
     *  con un for each se comprueba si el juego a añadir ya existe o no
     *  En caso de que exista y el juego se haya mantenido intacto salta una alerta avisando que el juego ya existe
     *  En caso de que exista pero alguno de sus apartados haya sido modificado,actualizará los datos del juego,saltando
     *  aviso de que el juego ha sido actualizado.
     *  En caso de que no exista el juego,lo añadirá al arraylist y saltará un aviso diciendo que el juego fue añadido con éxito
     */
    @FXML
    private void GuardarJuego() throws IOException {

        boolean existe = false;

        for (Videojuego juego : biblio.getJuegos()) {

            if (juego.getNombre().equalsIgnoreCase(Nombre.getText().trim())
            && juego.getAnosalida()==Integer.parseInt(Salida.getText())
            && juego.getDesarrollador().equalsIgnoreCase(Desarrollador.getText())
            && juego.getGenero().equalsIgnoreCase(Genero.getText())
            && juego.getPlataforma().equalsIgnoreCase(Plataforma.getText())) {
                IrAtras();
                existe = true;
                break;
            }else if(juego.getNombre().equalsIgnoreCase(Nombre.getText().trim())
                    && (juego.getAnosalida()!=Integer.parseInt(Salida.getText())
                    || !juego.getDesarrollador().equalsIgnoreCase(Desarrollador.getText())
                    || !juego.getGenero().equalsIgnoreCase(Genero.getText())
                    || !juego.getPlataforma().equalsIgnoreCase(Plataforma.getText()))) {

                juego.setAnosalida(Integer.parseInt(Salida.getText()));
                juego.setDesarrollador(Desarrollador.getText());
                juego.setGenero(Genero.getText());
                juego.setPlataforma(Plataforma.getText());

                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Videojuego actualizado");
                alerta.setHeaderText(null);
                alerta.setContentText("Este videojuego ha sido actualizado con éxito");
                alerta.showAndWait();
                IrAtras();
                break;
            }
        }

        if (existe) {

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Videojuego existente");
            alerta.setHeaderText(null);
            alerta.setContentText("Este videojuego ya está en la biblioteca.");
            alerta.showAndWait();
            IrAtras();

        } else {

            try {

                // Comprobamos que los campos no estén vacíos.
                if (Nombre.getText().isBlank()
                        || Salida.getText().isBlank()
                        || Desarrollador.getText().isBlank()
                        || Plataforma.getText().isBlank()
                        || Genero.getText().isBlank()) {

                    throw new IllegalArgumentException();
                }

                // Creamos el nuevo videojuego.
                Videojuego nuevoJuego = new Videojuego(
                        Nombre.getText().trim(),
                        Integer.parseInt(Salida.getText()),
                        Desarrollador.getText().trim(),
                        Plataforma.getText().trim(),
                        Genero.getText().trim()
                );

                // Lo añadimos a la biblioteca compartida.
                biblio.anadirJuego(nuevoJuego);

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Videojuego guardado");
                alerta.setHeaderText(null);
                alerta.setContentText("El videojuego se ha añadido correctamente.");
                alerta.showAndWait();
                IrAtras();
            } catch (NumberFormatException e) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Introduce un año de salida válido.");
                alerta.showAndWait();

            } catch (IllegalArgumentException e) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Debes rellenar todos los campos.");
                alerta.showAndWait();
            }
        }
    }
    public void setBiblioteca(BibliotecaJuegos biblio) {
        this.biblio = biblio;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
        buscarJuego();
    }

    /**
     * Metodo para localizar el juego en el array y poner la información del mismo para mostrarla.
     */
    private void buscarJuego() {

        for (Videojuego juego : biblio.getJuegos()) {

            if (juego.getNombre().equalsIgnoreCase(nombreJuego)) {

                Nombre.setText(juego.getNombre());
                Salida.setText(String.valueOf(juego.getAnosalida()));
                Desarrollador.setText(juego.getDesarrollador());
                Plataforma.setText(juego.getPlataforma());
                Genero.setText(juego.getGenero());

                return;
            }
        }

        // Si no encuentra el videojuego, dejamos los campos preparados.
        Nombre.setText(nombreJuego);
        Salida.clear();
        Desarrollador.clear();
        Plataforma.clear();
        Genero.clear();
    }
}
