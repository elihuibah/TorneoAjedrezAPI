package com.TorneoAjedrezAPI.repositorio;

import com.TorneoAjedrezAPI.modelo.Jugador;
import com.TorneoAjedrezAPI.modelo.Partida;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Slf4j
public class PartidaRepositorio {
    private final List<Partida> partidas = new ArrayList<>();

    private Long partidaId = 1L;

    public Partida registrarPartida(Partida partida, Jugador jugadorBlancas, Jugador jugadorNegras){
        partida.setPartidaId(partidaId++);
        partida.setJugadorBlancasId(jugadorBlancas.getJugadorId());
        partida.setJugadorNegrasId(jugadorNegras.getJugadorId());
        partidas.add(partida);
        return partida;
    }

    public void finalizarPartida(Partida partida, String resultado, int tiempoTotal){
        partida.setResultado(resultado);
        partida.setTiempoTotal(tiempoTotal);
        log.info("Partida finalizada");
    }

    public Partida getPartidaPorId(Long id) {
        return partidas.stream().filter(partida  -> partida.getPartidaId().equals(id)).findFirst().orElse(null);
    }
}
