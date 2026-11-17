package org.example.modelo;

import java.util.ArrayList;

public class BibliotecaJuegosModelo {
    private ArrayList<VideojuegoModelo> juegos;

    // Constructor
    public BibliotecaJuegosModelo() {
        juegos = new ArrayList<>();
    }

    // Añadir un videojuego a la biblioteca
    public void anadirJuego(VideojuegoModelo juego) {
        juegos.add(juego);
    }

    /**
     * Devuelve la lista de videojuegos almacenados
     * actualmente en la biblioteca.
     *
     * @return lista de videojuegos de la biblioteca
     */
    public ArrayList<VideojuegoModelo> getJuegos() {
        return juegos;
    }

}
