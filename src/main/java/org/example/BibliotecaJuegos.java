package org.example;

import java.util.ArrayList;

public class BibliotecaJuegos {
    private ArrayList<Videojuego> juegos;

    // Constructor
    public BibliotecaJuegos() {
        juegos = new ArrayList<>();
    }

    // Añadir un videojuego a la biblioteca
    public void anadirJuego(Videojuego juego) {
        juegos.add(juego);
    }

    /**
     * Devuelve la lista de videojuegos almacenados
     * actualmente en la biblioteca.
     *
     * @return lista de videojuegos de la biblioteca
     */
    public ArrayList<Videojuego> getJuegos() {
        return juegos;
    }

}
