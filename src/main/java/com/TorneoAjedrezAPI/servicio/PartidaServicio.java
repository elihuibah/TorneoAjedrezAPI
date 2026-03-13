package com.TorneoAjedrezAPI.servicio;

import com.TorneoAjedrezAPI.modelo.EstadoPartidaEnum;
import com.TorneoAjedrezAPI.modelo.Jugador;
import com.TorneoAjedrezAPI.modelo.Partida;
import com.TorneoAjedrezAPI.repositorio.PartidaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class PartidaServicio {
    private final PartidaRepositorio partidaRepositorio;

    public PartidaServicio(PartidaRepositorio partidaRepositorio) {
        this.partidaRepositorio = partidaRepositorio;
    }

    public Partida crearPartida(Partida partida, Jugador jugadorBlancas, Jugador jugadorNegras){
        boolean mismoJugador = jugadorBlancas.getJugadorId().equals(jugadorNegras.getJugadorId());
        if(mismoJugador){
            throw new IllegalArgumentException("El jugador " + jugadorBlancas.getNombreCompleto() + " no puede jugar contra él mismo.");
        }
        if(jugadorBlancas == null){
            throw new IllegalArgumentException("No se designó jugador para controlar las piezas blancas.");
        }
        if(jugadorNegras == null){
            throw new IllegalArgumentException("No se designó jugador para controlar las piezas negras.");
        }
        if(partida.getRitmo() == null){
            throw new IllegalArgumentException("La partida a crear no tiene ritmo especificado. Favor de completar todos los datos.");
        }
        boolean aperturaEspecificada = partida.getApertura() != null;
        if(partida.getEstado() == EstadoPartidaEnum.PENDIENTE){
            if(partida.getPuntajeBlancas() != 0 || partida.getPuntajeNegras() != 0){
                throw new IllegalArgumentException("Se asignaron puntajes en una partida especificada como " + partida.getEstado() + ". Favor de intentar de nuevo.");
            }
            if(aperturaEspecificada){
                throw new IllegalArgumentException("Se determinó apertura en una partida especificada como " + partida.getEstado() + ". Favor de intentar de nuevo.");
            }
        }
        if(partida.getEstado() != EstadoPartidaEnum.PENDIENTE && partida.getApertura() == null){
            throw new IllegalArgumentException("No se designó apertura para la partida especificada como " + partida.getEstado() + ". Favor de intentar de nuevo.");
        }
        if(partida.getEstado() == EstadoPartidaEnum.EN_PROCESO && (partida.getPuntajeBlancas() != 0 || partida.getPuntajeNegras() != 0)){
            throw new IllegalArgumentException("Se asignaron puntajes en una partida especificada como " + partida.getEstado() + ". Favor de intentar de nuevo.");
        }

        return partidaRepositorio.registrarPartida(partida, jugadorBlancas, jugadorNegras);
    }

    public Partida finalizarPartida(Partida partida, String resultado, int tiempoTotal) {
        if(partida == null){
            throw new IllegalArgumentException("La partida a finalizar no existe. Favor de intentar de nuevo.");
        }
        if(partida.getEstado() != EstadoPartidaEnum.EN_PROCESO){
            throw new IllegalArgumentException("La partida a finalizar no está en curso. Favor de intentar de nuevo.");
        }
        if(partida.getNumJugadas() < 1) {
            throw new IllegalArgumentException("La partida no puede finalizar sin ninguna jugada realizada. Favor de intentar de nuevo");
        }
        if((partida.getPuntajeBlancas() != 1 && partida.getPuntajeBlancas() != 0 && partida.getPuntajeBlancas() != 0.5)){
            throw new IllegalArgumentException("El puntaje asignado al jugador de las piezas blancas es incorrecto. Favor de intentar de nuevo");
        }
        if((partida.getPuntajeNegras() != 1 && partida.getPuntajeNegras() != 0 && partida.getPuntajeNegras() != 0.5)){
            throw new IllegalArgumentException("El puntaje asignado al jugador de las piezas blancas es incorrecto. Favor de intentar de nuevo");
        }
        int limiteMinutosRitmo = partida.getRitmo().getMaxDuracionMinutos();
        if(partida.getTiempoTotal() > limiteMinutosRitmo) {
            throw new IllegalArgumentException("El tiempo designado a la partida (de " + partida.getTiempoTotal() + " minutos)" +
                    "supera el limite de minutos permitido para el ritmo establecido, " + partida.getRitmo() + ", de " + limiteMinutosRitmo + " min");
        }
        return partidaRepositorio.finalizarPartida(partida, resultado, tiempoTotal);
    }

    public Partida getPartida(Long id) {
        return partidaRepositorio.getPartida(id);
    }

}