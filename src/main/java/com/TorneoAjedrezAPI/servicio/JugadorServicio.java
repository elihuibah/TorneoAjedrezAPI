package com.TorneoAjedrezAPI.servicio;

import com.TorneoAjedrezAPI.modelo.Jugador;
import com.TorneoAjedrezAPI.repositorio.JugadorRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorServicio {

    private final JugadorRepositorio jugadorRepositorio;

    public JugadorServicio(JugadorRepositorio jugadorRepositorio) {
        this.jugadorRepositorio = jugadorRepositorio;
    }

    public Jugador crearJugador(Jugador jugador) {
        return jugadorRepositorio.crearJugador(jugador);
    }

    public List<Jugador> getJugadores() {
        return jugadorRepositorio.getJugadores();
    }

    public Jugador getJugador(Long id) {
        return jugadorRepositorio.getJugadorPorId(id);
    }

    public Jugador actualizarJugador(Long id, Jugador jugadorActualizar) {
        return jugadorRepositorio.actualizarJugador(id, jugadorActualizar);
    }

    public boolean borrarJugador(Long id) {
        return jugadorRepositorio.eliminarJugador(id);
    }

}
