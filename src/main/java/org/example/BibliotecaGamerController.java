package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.ListView;

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
    }

    /**
     * Actualiza el contenido del ListView dependiendo de la
     * categoría seleccionada en el TreeView.
     *
     * @param categoria nombre de la categoría seleccionada
     */
    private void mostrarVideojuegos(String categoria) {

        /*
         * Antes de mostrar nuevos videojuegos limpiamos
         * el contenido anterior del ListView.
         */
        listaVideojuegos.getItems().clear();

        /*
         * Dependiendo de la categoría seleccionada,
         * mostramos diferentes videojuegos.
         */
        switch (categoria) {

            case "Jugando":
                listaVideojuegos.getItems().addAll(
                        "Minecraft",
                        "Hollow Knight",
                        "Stardew Valley"
                );
                break;

            case "Terminados":
                listaVideojuegos.getItems().addAll(
                        "Resident Evil 4",
                        "Portal 2",
                        "The Last of Us"
                );
                break;

            case "Pendientes":
                listaVideojuegos.getItems().addAll(
                        "Baldur's Gate 3",
                        "Celeste",
                        "Dark Souls"
                );
                break;

            default:
                break;
        }
    }

    /**
     * ListView utilizado para mostrar los videojuegos
     * correspondientes a la categoría seleccionada en el TreeView.
     *
     * Está conectado con el componente cuyo fx:id
     * es "listaVideojuegos" en el archivo FXML.
     */
    @FXML
    private ListView<String> listaVideojuegos;

}