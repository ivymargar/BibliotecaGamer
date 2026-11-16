package navegacion;

import java.util.List;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

/**
 * Controlador de la pantalla de navegación general de la aplicación GAME VAULT.
 * <p>
 * Gestiona tres componentes:
 * <ul>
 *   <li>{@link ToolBar}: barra superior con los botones Inicio, Biblioteca, Añadir y Perfil.</li>
 *   <li>{@link Pagination}: paginación del catálogo de videojuegos.</li>
 *   <li>{@link Hyperlink}: enlace con más información sobre el videojuego.</li>
 * </ul>
 *
 * @author Iván Márquez Sánchez
 * @version 1.0
 */
public class NavegacionController {

    /** Número de videojuegos que se muestran en cada página. */
    private static final int JUEGOS_POR_PAGINA = 3;

    /** Dirección que abre el hipervínculo (de momento, una página de ejemplo). */
    private static final String URL_INFO = "https://es.wikipedia.org/wiki/Videojuego";

    /** Barra de herramientas de navegación. */
    @FXML
    private ToolBar barraNavegacion;

    /** Botón para ir a la pantalla de inicio. */
    @FXML
    private Button btnInicio;

    /** Botón para ir a la biblioteca de juegos. */
    @FXML
    private Button btnBiblioteca;

    /** Botón para añadir un nuevo videojuego. */
    @FXML
    private Button btnAnadir;

    /** Botón para ir al perfil del usuario. */
    @FXML
    private Button btnPerfil;

    /** Componente de paginación del catálogo. */
    @FXML
    private Pagination paginacion;

    /** Enlace con más información sobre el videojuego. */
    @FXML
    private Hyperlink enlaceInfo;

    /** Etiqueta que indica en qué sección estamos. */
    @FXML
    private Label lblEstado;

    /** Lista de videojuegos de ejemplo (más adelante vendrá de la parte del catálogo). */
    private final List<String> juegos = List.of(
            "Resident Evil 4", "The Last of Us", "Hollow Knight",
            "Zelda: Breath of the Wild", "God of War", "Celeste",
            "Hades", "Stardew Valley", "Elden Ring", "Portal 2");

    /** Servicio de JavaFX necesario para abrir el navegador web. */
    private HostServices hostServices;

    /**
     * Método que se ejecuta automáticamente al cargar el FXML.
     * Configura el número de páginas y cómo se construye cada una.
     */
    @FXML
    public void initialize() {
        int totalPaginas = (int) Math.ceil((double) juegos.size() / JUEGOS_POR_PAGINA);
        paginacion.setPageCount(totalPaginas);
        paginacion.setPageFactory(this::crearPagina);
    }

    /**
     * Construye el contenido de una página del catálogo.
     *
     * @param indicePagina índice de la página (empieza en 0)
     * @return un {@link VBox} con los juegos que corresponden a esa página
     */
    private VBox crearPagina(int indicePagina) {
        VBox contenedor = new VBox(10);
        contenedor.setStyle("-fx-padding: 20;");

        Label titulo = new Label("Juegos - Página " + (indicePagina + 1));
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        contenedor.getChildren().add(titulo);

        int inicio = indicePagina * JUEGOS_POR_PAGINA;
        int fin = Math.min(inicio + JUEGOS_POR_PAGINA, juegos.size());

        for (int i = inicio; i < fin; i++) {
            Label tarjeta = new Label("🎮 " + juegos.get(i));
            tarjeta.setStyle("-fx-border-color: gray; -fx-padding: 10; -fx-font-size: 14px;");
            tarjeta.setMaxWidth(Double.MAX_VALUE);
            contenedor.getChildren().add(tarjeta);
        }
        return contenedor;
    }

    /**
     * Asigna el servicio {@link HostServices} para poder abrir enlaces en el navegador.
     *
     * @param hostServices servicios de la aplicación, obtenidos desde la clase Application
     */
    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    /** Acción del botón Inicio. */
    @FXML
    private void onInicio() {
        lblEstado.setText("Sección: Inicio");
    }

    /** Acción del botón Biblioteca. */
    @FXML
    private void onBiblioteca() {
        lblEstado.setText("Sección: Biblioteca");
    }

    /** Acción del botón Añadir. */
    @FXML
    private void onAnadir() {
        lblEstado.setText("Sección: Añadir videojuego");
    }

    /** Acción del botón Perfil. */
    @FXML
    private void onPerfil() {
        lblEstado.setText("Sección: Perfil");
    }

    /**
     * Acción del hipervínculo: abre la página web de información en el navegador.
     */
    @FXML
    private void onEnlaceInfo() {
        if (hostServices != null) {
            hostServices.showDocument(URL_INFO);
        }
        // Quitamos el estado "visitado" para que el enlace siempre se vea igual
        enlaceInfo.setVisited(false);
    }
}
