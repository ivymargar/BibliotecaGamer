package org.example.modelo;

/**
 * Modelo que representa un videojuego almacenado en Biblioteca Gamer.
 *
 * Contiene tanto la información general del videojuego como
 * los datos personales introducidos por el usuario.
 */
public class VideojuegoModelo {

    private String nombre;
    private int anosalida;
    private String desarrollador;
    private String plataforma;
    private String genero;

    /*
     * Datos introducidos desde la pantalla
     * Añadir o Actualizar Juego.
     */
    private double puntuacion;
    private boolean tieneLogros;
    private int logrosCompletados;
    private int logrosTotales;
    private boolean completado;

    /**
     * Constructor del videojuego.
     *
     * Se mantiene el constructor original de cinco parámetros
     * para no modificar el código que ya crea videojuegos.
     *
     * @param nombre nombre del videojuego
     * @param anosalida año de salida
     * @param desarrollador empresa desarrolladora
     * @param plataforma plataforma del videojuego
     * @param genero género del videojuego
     */
    public VideojuegoModelo(
            String nombre,
            int anosalida,
            String desarrollador,
            String plataforma,
            String genero) {

        this.nombre = nombre;
        this.anosalida = anosalida;
        this.desarrollador = desarrollador;
        this.plataforma = plataforma;
        this.genero = genero;

        /*
         * Valores iniciales de los datos opcionales.
         */
        this.puntuacion = 0.0;
        this.tieneLogros = false;
        this.logrosCompletados = 0;
        this.logrosTotales = 0;
        this.completado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnosalida() {
        return anosalida;
    }

    public void setAnosalida(int anosalida) {
        this.anosalida = anosalida;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public boolean isTieneLogros() {
        return tieneLogros;
    }

    public void setTieneLogros(boolean tieneLogros) {
        this.tieneLogros = tieneLogros;
    }

    public int getLogrosCompletados() {
        return logrosCompletados;
    }

    public void setLogrosCompletados(int logrosCompletados) {
        this.logrosCompletados = logrosCompletados;
    }

    public int getLogrosTotales() {
        return logrosTotales;
    }

    public void setLogrosTotales(int logrosTotales) {
        this.logrosTotales = logrosTotales;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}