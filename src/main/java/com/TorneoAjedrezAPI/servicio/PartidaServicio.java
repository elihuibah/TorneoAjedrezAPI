package com.TorneoAjedrezAPI.servicio;

import com.TorneoAjedrezAPI.modelo.Jugador;
import com.TorneoAjedrezAPI.modelo.Partida;
import com.TorneoAjedrezAPI.repositorio.PartidaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaServicio {
    private final PartidaRepositorio partidaRepositorio;

    public PartidaServicio(PartidaRepositorio partidaRepositorio) {
        this.partidaRepositorio = partidaRepositorio;
    }

    public Partida crearPartida(Partida partida, Jugador jugadorBlancas, Jugador jugadorNegras){

    }


}
