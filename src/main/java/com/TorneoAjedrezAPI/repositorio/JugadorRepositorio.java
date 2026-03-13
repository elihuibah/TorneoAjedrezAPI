package com.TorneoAjedrezAPI.repositorio;

import com.TorneoAjedrezAPI.modelo.Jugador;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class JugadorRepositorio {
    private final List<Jugador> jugadores = new ArrayList<>();

    private Long jugadorId = 1L;

    public Jugador crearJugador(Jugador jugador) {
        jugador.setJugadorId(jugadorId++);
        jugadores.add(jugador);
        return jugador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador getJugadorPorId(Long id) {
        return jugadores.stream().filter(jugador -> jugador.getJugadorId().equals(id)).findFirst().orElse(null);
    }

    public Jugador actualizarJugador(Long id, Jugador jugadorActualizar) {
        for(Jugador jugador : jugadores){
            if(jugador.getJugadorId().equals(id)){
                jugador.setNombreCompleto(jugadorActualizar.getNombreCompleto());
                jugador.setGenero(jugadorActualizar.getGenero());
                jugador.setEdad(jugadorActualizar.getEdad());
                jugador.setElo(jugadorActualizar.getElo());
                jugador.setEdad(jugadorActualizar.getEdad());
                jugador.setNacionalidad(jugadorActualizar.getNacionalidad());
                return jugador;
            }
        }
        return null;
    }

    public boolean eliminarJugador(Long id) {
        return jugadores.removeIf(jugador -> jugador.getJugadorId().equals(id));
    }


}
