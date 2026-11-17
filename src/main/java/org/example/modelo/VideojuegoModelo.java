package org.example.modelo;

public class VideojuegoModelo {
    String nombre;
    int anosalida;
    String desarrollador;
    String plataforma;
    String genero;

    public VideojuegoModelo(String nombre, int anosalida, String desarrollador, String plataforma, String genero){
        this.nombre=nombre;
        this.anosalida=anosalida;
        this.desarrollador=desarrollador;
        this.plataforma=plataforma;
        this.genero=genero;
    }
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

}
