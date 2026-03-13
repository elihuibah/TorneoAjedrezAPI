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
        partida.setRitmo(partida.getRitmo());
        partida.setEstado(partida.getEstado());
        partida.setPuntajeBlancas(partida.getPuntajeBlancas());
        partida.setPuntajeNegras(partida.getPuntajeNegras());
        partidas.add(partida);
        log.info("Partida registrada con éxito.");
        return partida;
    }

    public Partida finalizarPartida(Partida partida, String resultado, int tiempoTotal){
        partida.setPuntajeBlancas(partida.getPuntajeBlancas());
        partida.setPuntajeNegras(partida.getPuntajeNegras());
        resultado = partida.getPuntajeBlancas() + " - " + partida.getPuntajeNegras();
        partida.setResultado(resultado);
        partida.setTiempoTotal(tiempoTotal);
        log.info("Partida finalizada con éxito.");
        return null;
    }

    public Partida getPartida(Long id) {
        return partidas.stream().filter(partida  -> partida.getPartidaId().equals(id)).findFirst().orElse(null);
    }

}
