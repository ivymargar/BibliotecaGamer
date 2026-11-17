package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class BibliotecaGamerController {

    @FXML
    private TreeView<String> arbolBiblioteca;

    @FXML
    private ListView<String> listaVideojuegos;

    @FXML
    private ToolBar barraNavegacion;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnBiblioteca;

    @FXML
    private Button btnAnadir;

    @FXML
    private Button btnPerfil;

    @FXML
    private Pagination paginacion;

    @FXML
    private Hyperlink enlaceInfo;

    @FXML
    private Label lblEstado;

    private static final int JUEGOS_POR_PAGINA = 5;

    private BibliotecaJuegos biblio = new BibliotecaJuegos();

    private final java.util.List<String> juegosCategoria =
            new java.util.ArrayList<>();

    @FXML
    public void initialize() {

        biblio.anadirJuego(new Videojuego(
                "Minecraft", 2009, "Mojang Studios", "PC", "Sandbox"
        ));

        biblio.anadirJuego(new Videojuego(
                "Hollow Knight", 2016, "Team Cherry", "PC", "Metroidvania"
        ));

        biblio.anadirJuego(new Videojuego(
                "Stardew Valley", 2016, "ConcernedApe", "PC", "Juego de Granjas"
        ));

        TreeItem<String> raiz =
                new TreeItem<>("🎮 VIDEOJUEGOS");

        TreeItem<String> pc =
                new TreeItem<>("PC");

        TreeItem<String> playStation =
                new TreeItem<>("PlayStation");

        TreeItem<String> nintendo =
                new TreeItem<>("Nintendo");

        raiz.getChildren().addAll(
                pc,
                playStation,
                nintendo
        );

        pc.getChildren().addAll(
                new TreeItem<>("Jugando"),
                new TreeItem<>("Terminados"),
                new TreeItem<>("Pendientes")
        );

        playStation.getChildren().addAll(
                new TreeItem<>("Jugando"),
                new TreeItem<>("Terminados"),
                new TreeItem<>("Pendientes")
        );

        nintendo.getChildren().addAll(
                new TreeItem<>("Jugando"),
                new TreeItem<>("Terminados"),
                new TreeItem<>("Pendientes")
        );

        arbolBiblioteca.setRoot(raiz);

        raiz.setExpanded(true);

        arbolBiblioteca.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, anterior, seleccionado) -> {

                    if (seleccionado != null) {

                        String categoria = seleccionado.getValue();

                        mostrarVideojuegos(categoria);
                    }
                });

        listaVideojuegos.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {

                String juegoSeleccionado =
                        listaVideojuegos.getSelectionModel().getSelectedItem();

                if (juegoSeleccionado != null) {

                    for (Videojuego juego : biblio.getJuegos()) {

                        // Comprobamos que el juego coincide con el seleccionado.
                        if (juego.getNombre().equals(juegoSeleccionado)) {

                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                            alerta.setTitle("Información del juego");
                            alerta.setHeaderText(null);

                            alerta.setContentText(
                                    "Nombre del juego: " + juego.getNombre() + "\n" +
                                            "Año de Salida: " + juego.getAnosalida() + "\n" +
                                            "Desarrollador: " + juego.getDesarrollador() + "\n" +
                                            "Plataforma: " + juego.getPlataforma() + "\n" +
                                            "Género: " + juego.getGenero()
                            );

                            alerta.showAndWait();

                            break;
                        }
                    }
                }
            }
        });

        paginacion.currentPageIndexProperty().addListener(
                (observable, paginaAnterior, paginaNueva) -> {

                    mostrarPagina(paginaNueva.intValue());
                }
        );
    }

    private void abrirInformacionJuego(String juego) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("revisionjuegos.fxml")
            );

            Parent root = loader.load();

            ControladorHojaJuegos controlador = loader.getController();

            controlador.setBiblioteca(biblio);
            controlador.setNombreJuego(juego);

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Información - " + juego);
            stage.setResizable(false);

            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void mostrarVideojuegos(String categoria) {

        juegosCategoria.clear();

        switch (categoria) {

            case "Jugando":
                juegosCategoria.addAll(
                        java.util.Arrays.asList(
                                "Minecraft",
                                "Hollow Knight",
                                "Stardew Valley",
                                "Hades",
                                "Celeste",
                                "Elden Ring",
                                "Terraria",
                                "Dead Cells",
                                "Cuphead",
                                "Subnautica"
                        )
                );
                break;

            case "Terminados":
                juegosCategoria.addAll(
                        java.util.Arrays.asList(
                                "Resident Evil 4",
                                "Portal 2",
                                "The Last of Us",
                                "God of War",
                                "The Witcher 3",
                                "Resident Evil 2",
                                "BioShock",
                                "Undertale",
                                "Detroit: Become Human",
                                "Life is Strange"
                        )
                );
                break;

            case "Pendientes":
                juegosCategoria.addAll(
                        java.util.Arrays.asList(
                                "Baldur's Gate 3",
                                "Dark Souls",
                                "Cyberpunk 2077",
                                "Horizon Zero Dawn",
                                "Sekiro",
                                "Death Stranding",
                                "Control",
                                "Alan Wake 2",
                                "Outer Wilds",
                                "Disco Elysium"
                        )
                );
                break;

            default:
                break;
        }

        int totalPaginas = (int) Math.ceil(
                (double) juegosCategoria.size() / JUEGOS_POR_PAGINA
        );

        paginacion.setPageCount(Math.max(totalPaginas, 1));

        paginacion.setCurrentPageIndex(0);

        mostrarPagina(0);
    }

    private void mostrarPagina(int indicePagina) {

        int inicio = indicePagina * JUEGOS_POR_PAGINA;

        int fin = Math.min(
                inicio + JUEGOS_POR_PAGINA,
                juegosCategoria.size()
        );

        listaVideojuegos.getItems().clear();

        for (int i = inicio; i < fin; i++) {

            listaVideojuegos.getItems().add(
                    juegosCategoria.get(i)
            );
        }
    }

    @FXML
    private void onInicio() {
        lblEstado.setText("Sección: Inicio");
    }

    @FXML
    private void onBiblioteca() {
        lblEstado.setText("Sección: Biblioteca");
    }

    @FXML
    private void onAnadir() {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("revisionjuegos.fxml")
            );

            Parent root = loader.load();

            ControladorHojaJuegos controlador = loader.getController();

            // Compartimos la biblioteca.
            controlador.setBiblioteca(biblio);

            // Abrimos la ventana.
            Scene scene = new Scene(root);

            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Añadir videojuego");

            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }

        lblEstado.setText("Sección: Añadir videojuego");
    }

    @FXML
    private void onPerfil() {
        lblEstado.setText("Sección: Perfil");
    }

    @FXML
    private void onEnlaceInfo() {
        enlaceInfo.setVisited(false);
    }
}
