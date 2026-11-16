package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ToolBar;

/**
 * Controlador principal de la aplicación Biblioteca Gamer.
 *
 * Gestiona la interacción entre los componentes definidos
 * en la vista FXML y la lógica de la aplicación.
 *
 * En esta primera fase se encarga de inicializar el TreeView
 * utilizado para organizar la biblioteca de videojuegos.
 *
 * @author Leo Martínez
 */
public class BibliotecaGamerController {

    /**
     * TreeView utilizado para mostrar las diferentes
     * categorías de la biblioteca de videojuegos.
     *
     * Está conectado con el componente cuyo fx:id
     * es "arbolBiblioteca" en el archivo FXML.
     */
    @FXML
    private TreeView<String> arbolBiblioteca;

    /**
     * ListView utilizado para mostrar los videojuegos
     * correspondientes a la categoría seleccionada en el TreeView.
     */
    @FXML
    private ListView<String> listaVideojuegos;


// ==========================================================
// COMPONENTES DE NAVEGACIÓN
// Desarrollados originalmente por Iván Márquez Sánchez
// e integrados en la pantalla principal Biblioteca Gamer.
// ==========================================================

    /**
     * Barra de herramientas utilizada para la navegación principal.
     */
    @FXML
    private ToolBar barraNavegacion;

    /**
     * Botón correspondiente a la sección Inicio.
     */
    @FXML
    private Button btnInicio;

    /**
     * Botón correspondiente a la biblioteca de videojuegos.
     */
    @FXML
    private Button btnBiblioteca;

    /**
     * Botón utilizado para acceder a la incorporación de videojuegos.
     */
    @FXML
    private Button btnAnadir;

    /**
     * Botón utilizado para acceder al perfil.
     */
    @FXML
    private Button btnPerfil;

    /**
     * Componente utilizado para paginar los videojuegos mostrados.
     */
    @FXML
    private Pagination paginacion;

    /**
     * Hipervínculo utilizado para acceder a información adicional.
     */
    @FXML
    private Hyperlink enlaceInfo;

    /**
     * Etiqueta que indica la sección actual de la aplicación.
     */
    @FXML
    private Label lblEstado;

// ==========================================================
// CONFIGURACIÓN DE PAGINATION
// Adaptación del componente desarrollado por Iván Márquez
// al ListView principal de Biblioteca Gamer.
// ==========================================================

    /**
     * Número máximo de videojuegos que se muestran
     * simultáneamente en cada página del ListView.
     */
    private static final int JUEGOS_POR_PAGINA = 5;

    /**
     * Lista que almacena temporalmente todos los videojuegos
     * correspondientes a la categoría seleccionada en el TreeView.
     *
     * Pagination utilizará esta lista para determinar qué juegos
     * debe mostrar en cada página.
     */
    private final java.util.List<String> juegosCategoria =
            new java.util.ArrayList<>();

    /**
     * Inicializa los componentes de la vista.
     *
     * Este método se ejecuta automáticamente después
     * de cargar el archivo FXML.
     */
    @FXML
    public void initialize() {

        /*
         * Creamos el elemento principal del árbol.
         * Será la raíz de toda nuestra biblioteca.
         */
        TreeItem<String> raiz =
                new TreeItem<>("🎮 VIDEOJUEGOS");

        /*
         * Creamos las diferentes plataformas.
         */
        TreeItem<String> pc =
                new TreeItem<>("PC");

        TreeItem<String> playStation =
                new TreeItem<>("PlayStation");

        TreeItem<String> nintendo =
                new TreeItem<>("Nintendo");

        /*
         * Añadimos las plataformas como hijos
         * del elemento raíz.
         */
        raiz.getChildren().addAll(
                pc,
                playStation,
                nintendo
        );

        /*
         * Creamos las categorías correspondientes a PC.
         */
        pc.getChildren().addAll(
                new TreeItem<>("Jugando"),
                new TreeItem<>("Terminados"),
                new TreeItem<>("Pendientes")
        );

        /*
         * Creamos las categorías correspondientes
         * a PlayStation.
         */
        playStation.getChildren().addAll(
                new TreeItem<>("Jugando"),
                new TreeItem<>("Terminados"),
                new TreeItem<>("Pendientes")
        );

        /*
         * Creamos las categorías correspondientes
         * a Nintendo.
         */
        nintendo.getChildren().addAll(
                new TreeItem<>("Jugando"),
                new TreeItem<>("Terminados"),
                new TreeItem<>("Pendientes")
        );

        /*
         * Asignamos al TreeView la raíz que acabamos
         * de construir.
         */
        arbolBiblioteca.setRoot(raiz);

        /*
         * Mostramos inicialmente desplegada la raíz.
         */
        raiz.setExpanded(true);

        /*
         * Escuchamos los cambios de selección realizados
         * por el usuario dentro del TreeView.
         */
        arbolBiblioteca.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, anterior, seleccionado) -> {

                    /*
                     * Comprobamos que realmente exista
                     * un elemento seleccionado.
                     */
                    if (seleccionado != null) {

                        /*
                         * Obtenemos el texto del elemento
                         * seleccionado en el árbol.
                         */
                        String categoria = seleccionado.getValue();

                        /*
                         * Actualizamos el ListView dependiendo
                         * de la categoría seleccionada.
                         */
                        mostrarVideojuegos(categoria);
                    }
                });

        /*
         * Detectamos el doble clic realizado sobre uno de los
         * videojuegos mostrados en el ListView.
         */
        listaVideojuegos.setOnMouseClicked(event -> {

            /*
             * Comprobamos que el usuario haya realizado
             * exactamente dos clics.
             */
            if (event.getClickCount() == 2) {

                /*
                 * Obtenemos el videojuego seleccionado.
                 */
                String juegoSeleccionado =
                        listaVideojuegos.getSelectionModel().getSelectedItem();

                /*
                 * Solo abrimos la ficha si realmente existe
                 * un videojuego seleccionado.
                 */
                if (juegoSeleccionado != null) {

                    abrirInformacionJuego(juegoSeleccionado);
                }
            }
        });

        /*
         * Escuchamos los cambios de página realizados
         * por el usuario en Pagination.
         */
        paginacion.currentPageIndexProperty().addListener(
                (observable, paginaAnterior, paginaNueva) -> {

                    /*
                     * Cuando cambia la página, mostramos en el ListView
                     * los videojuegos correspondientes a la nueva página.
                     */
                    mostrarPagina(paginaNueva.intValue());
                }
        );
    }

    /**
     * Abre una nueva ventana con la información
     * correspondiente al videojuego seleccionado.
     *
     * @param juego nombre del videojuego seleccionado
     */
    private void abrirInformacionJuego(String juego) {

        try {

            /*
             * Cargamos el archivo FXML correspondiente
             * a la ficha de información del videojuego.
             */
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("revisionjuegos.fxml")
            );

            Parent root = loader.load();

            /*
             * Creamos una nueva escena utilizando
             * el contenido del archivo FXML.
             */
            Scene scene = new Scene(root);

            /*
             * Creamos una nueva ventana independiente
             * para mostrar la ficha del videojuego.
             */
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Información - " + juego);
            stage.setResizable(false);

            /*
             * Mostramos la nueva ventana.
             */
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * Actualiza los videojuegos disponibles dependiendo de la
     * categoría seleccionada en el TreeView.
     *
     * Los videojuegos se almacenan primero en una lista completa.
     * Posteriormente, Pagination se encarga de mostrar únicamente
     * los correspondientes a la página seleccionada.
     *
     * @param categoria nombre de la categoría seleccionada
     */
    private void mostrarVideojuegos(String categoria) {

        /*
         * Eliminamos los videojuegos pertenecientes
         * a la selección anterior.
         */
        juegosCategoria.clear();

        /*
         * Dependiendo de la categoría seleccionada,
         * cargamos diez videojuegos diferentes.
         */
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
                /*
                 * Si se selecciona un elemento que no representa
                 * una categoría, dejamos la lista vacía.
                 */
                break;
        }

        /*
         * Calculamos cuántas páginas necesitamos.
         * Cada página puede mostrar un máximo de cinco juegos.
         */
        int totalPaginas = (int) Math.ceil(
                (double) juegosCategoria.size() / JUEGOS_POR_PAGINA
        );

        /*
         * Pagination debe disponer como mínimo de una página.
         */
        paginacion.setPageCount(Math.max(totalPaginas, 1));

        /*
         * Siempre volvemos a la primera página cuando
         * seleccionamos una categoría diferente.
         */
        paginacion.setCurrentPageIndex(0);

        /*
         * Mostramos los primeros cinco videojuegos.
         */
        mostrarPagina(0);
    }

    /**
     * Muestra en el ListView únicamente los videojuegos
     * correspondientes a la página seleccionada.
     *
     * Cada página muestra como máximo cinco videojuegos.
     *
     * @param indicePagina índice de la página seleccionada,
     *                     comenzando desde 0
     */
    private void mostrarPagina(int indicePagina) {

        /*
         * Calculamos la posición del primer videojuego
         * correspondiente a la página seleccionada.
         *
         * Página 0 -> posición 0
         * Página 1 -> posición 5
         */
        int inicio = indicePagina * JUEGOS_POR_PAGINA;

        /*
         * Calculamos la posición final.
         * Math.min evita superar el tamaño real de la lista.
         */
        int fin = Math.min(
                inicio + JUEGOS_POR_PAGINA,
                juegosCategoria.size()
        );

        /*
         * Limpiamos los videojuegos de la página anterior.
         */
        listaVideojuegos.getItems().clear();

        /*
         * Añadimos únicamente los videojuegos comprendidos
         * entre las posiciones inicio y fin.
         */
        for (int i = inicio; i < fin; i++) {

            listaVideojuegos.getItems().add(
                    juegosCategoria.get(i)
            );
        }
    }

    // ==========================================================
// MÉTODOS DE NAVEGACIÓN
// Desarrollados originalmente por Iván Márquez Sánchez
// e integrados en BibliotecaGamerController.
// ==========================================================

    /**
     * Gestiona la acción del botón Inicio.
     * Actualiza la etiqueta inferior indicando la sección seleccionada.
     */
    @FXML
    private void onInicio() {
        lblEstado.setText("Sección: Inicio");
    }

    /**
     * Gestiona la acción del botón Biblioteca.
     * Actualiza la etiqueta inferior indicando que el usuario
     * se encuentra en la biblioteca de videojuegos.
     */
    @FXML
    private void onBiblioteca() {
        lblEstado.setText("Sección: Biblioteca");
    }

    /**
     * Gestiona la acción del botón Añadir.
     * De momento actualiza la etiqueta de estado.
     */
    @FXML
    private void onAnadir() {
        lblEstado.setText("Sección: Añadir videojuego");
    }

    /**
     * Gestiona la acción del botón Perfil.
     * De momento actualiza la etiqueta de estado.
     */
    @FXML
    private void onPerfil() {
        lblEstado.setText("Sección: Perfil");
    }

    /**
     * Gestiona la acción realizada sobre el Hyperlink.
     *
     * De momento se mantiene preparado para incorporar posteriormente
     * el acceso a información adicional sobre el videojuego.
     */
    @FXML
    private void onEnlaceInfo() {
        enlaceInfo.setVisited(false);
    }
}