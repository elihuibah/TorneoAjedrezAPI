package com.TorneoAjedrezAPI.servicio;

import com.TorneoAjedrezAPI.exception.RecursoNoEncontrado;
import com.TorneoAjedrezAPI.modelo.EstadoPartidaEnum;
import com.TorneoAjedrezAPI.modelo.Jugador;
import com.TorneoAjedrezAPI.modelo.Partida;
import com.TorneoAjedrezAPI.repositorio.PartidaRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PartidaServicio {
    private final PartidaRepositorio partidaRepositorio;

    public PartidaServicio(PartidaRepositorio partidaRepositorio) {
        this.partidaRepositorio = partidaRepositorio;
    }

    public Partida crearPartida(Partida partida, Jugador jugadorBlancas, Jugador jugadorNegras){
        if(partida == null){
            log.info("Error. Partida designada como nula.");
            throw new RecursoNoEncontrado("La partida no puede ser nula.");
        }
        if(jugadorBlancas == null){
            log.info("Error. Jugador de blancas no encontrado.");
            throw new RecursoNoEncontrado("No se designó jugador para controlar las piezas blancas.");
        }
        if(jugadorNegras == null){
            log.info("Error. Jugador de negras no encontrado.");
            throw new RecursoNoEncontrado("No se designó jugador para controlar las piezas negras.");
        }

        boolean mismoJugador = jugadorBlancas.getJugadorId().equals(jugadorNegras.getJugadorId());
        if(mismoJugador){
            log.info("Error. Mismo jugador designado para ambos lados.");
            throw new IllegalArgumentException("El jugador " + jugadorBlancas.getNombreCompleto() + " no puede jugar contra él mismo.");
        }

        if(partida.getRitmo() == null){
            log.info("Error. Ritmo no especificado.");
            throw new RecursoNoEncontrado("La partida a crear no tiene ritmo especificado. Favor de completar todos los datos.");
        }
        boolean aperturaEspecificada = partida.getApertura() != null;
        if(partida.getEstado() == EstadoPartidaEnum.PENDIENTE){
            if(partida.getPuntajeBlancas() != 0 || partida.getPuntajeNegras() != 0){
                log.info("Error. Puntaje asignado a partida pendiente.");
                throw new IllegalArgumentException("Se asignaron puntajes en una partida especificada como " + partida.getEstado() + ". Favor de intentar de nuevo.");
            }
            if(aperturaEspecificada){
                log.info("Error. Apertura asignada en partida pendiente.");
                throw new IllegalArgumentException("Se determinó apertura en una partida especificada como " + partida.getEstado() + ". Favor de intentar de nuevo.");
            }
        }
        if(partida.getEstado() != EstadoPartidaEnum.PENDIENTE && partida.getApertura() == null){
            log.info("Error. Puntaje asignado a partida pendiente.");
            throw new IllegalArgumentException("No se designó apertura para la partida especificada como " + partida.getEstado() + ". Favor de intentar de nuevo.");
        }
        if(partida.getEstado() == EstadoPartidaEnum.EN_PROCESO && (partida.getPuntajeBlancas() != 0 || partida.getPuntajeNegras() != 0)){
            log.info("Error. Puntaje asignado a partida en proceso.");
            throw new IllegalArgumentException("Se asignaron puntajes en una partida especificada como " + partida.getEstado() + ". Favor de intentar de nuevo.");
        }

        return partidaRepositorio.registrarPartida(partida, jugadorBlancas, jugadorNegras);
    }

    public Partida finalizarPartida(Long partidaId, String resultado, int tiempoTotal) {
        Partida partida = partidaRepositorio.getPartida(partidaId);
        if(partida == null){
            log.info("Error. Partida a finalizar inexistente.");
            throw new RecursoNoEncontrado("La partida a finalizar no existe. Favor de intentar de nuevo.");
        }
        if(partida.getEstado() != EstadoPartidaEnum.EN_PROCESO){
            log.info("Error. Partida no iniciada o ya finalizada.");
            throw new IllegalArgumentException("La partida a finalizar no está en curso. Favor de intentar de nuevo.");
        }
        if(partida.getNumJugadas() < 1) {
            log.info("Error. Número de jugadas inválidas.");
            throw new IllegalArgumentException("La partida no puede finalizar sin ninguna jugada realizada. Favor de intentar de nuevo");
        }

        switch(partida.getResultado()){
            case "1-0":
                partida.setPuntajeBlancas(1);
                partida.setPuntajeNegras(0);
                break;
            case "0-1":
                partida.setPuntajeBlancas(0);
                partida.setPuntajeNegras(1);
                break;
            case "0.5-0.5":
                partida.setPuntajeBlancas(0.5);
                partida.setPuntajeNegras(0.5);
            default:
                log.info("Error. El resultado asignado es incorrecto.");
                throw new IllegalArgumentException("Resultado inválido. Los formatos aceptados son '1-0', '0-1', '1/2-1/2' o '0.5-0.5'.");
        }

        int minRitmo = partida.getRitmo().getMinDuracionMinutos();
        int maxRitmo = partida.getRitmo().getMaxDuracionMinutos();

        if(tiempoTotal < minRitmo || tiempoTotal > maxRitmo){
            log.info("Error al final la partida: Tiempo {} min fuera del rango {} - {}", tiempoTotal, minRitmo, maxRitmo);
            throw new IllegalArgumentException("El tiempo total no coincide con el rango del ritmo establecido.");
        }

        partida.setEstado(EstadoPartidaEnum.FINALIZADA);
        partida.setResultado(resultado);
        log.info("Partida {} finalizada exitosamente con resultado {}", partidaId, resultado);
        return partidaRepositorio.finalizarPartida(partida, resultado, tiempoTotal);
    }

    public Partida getPartida(Long id) {
        Partida partida =  partidaRepositorio.getPartida(id);
        log.info("Partida {} encontrada.", partida);
        return partidaRepositorio.getPartida(id);
    }

}