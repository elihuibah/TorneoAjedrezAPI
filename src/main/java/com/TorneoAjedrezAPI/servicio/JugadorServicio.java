package com.TorneoAjedrezAPI.servicio;

import com.TorneoAjedrezAPI.exception.PartidaActiva;
import com.TorneoAjedrezAPI.exception.RecursoNoEncontrado;
import com.TorneoAjedrezAPI.modelo.EstadoPartidaEnum;
import com.TorneoAjedrezAPI.modelo.Jugador;
import com.TorneoAjedrezAPI.repositorio.JugadorRepositorio;
import com.TorneoAjedrezAPI.repositorio.PartidaRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JugadorServicio {

    private final JugadorRepositorio jugadorRepositorio;
    private final PartidaRepositorio partidaRepositorio;

    public JugadorServicio(JugadorRepositorio jugadorRepositorio, PartidaRepositorio partidaRepositorio) {
        this.jugadorRepositorio = jugadorRepositorio;
        this.partidaRepositorio = partidaRepositorio;
    }

    public Jugador crearJugador(Jugador jugador) {
        boolean nombreInvalido = jugador.getNombreCompleto().isEmpty();
        boolean nacionalidadInvalida =  jugador.getNacionalidad().isEmpty();

        if (nombreInvalido || nacionalidadInvalida) {
            log.info("Error. Nombre de jugador inexistente."); //log para registrar la excepción
            throw new RecursoNoEncontrado("No se introdujo el nombre adecuadamente. Favor de intentar de nuevo.");
        }

        if(jugador.getElo() <= 0){
            log.info("Error. ELO de jugador inexistente.");
                throw new IllegalArgumentException("El puntaje ELO ingresado es menor o igual a 0. Favor de intentar de nuevo.");
        }

        log.info("El jugador {} ha sido creado exitosamente.", jugador.getNombreCompleto());
        return jugadorRepositorio.crearJugador(jugador);
    }

    public List<Jugador> getTodosJugadores() {
        log.info("Desplegando lista de jugadores.");
        return jugadorRepositorio.getTodosJugadores();
    }

    public Jugador getJugador(Long jugadorId) {
        Jugador jugador = jugadorRepositorio.getJugadorPorId(jugadorId);
        if (jugador == null) {
            log.info("Error. Jugador inexistente.");
            throw new RecursoNoEncontrado("Jugador no encontrado. Favor de intentar de nuevo.");
        }
        log.info("Jugador {} encontrado.", jugador.getNombreCompleto());
        return jugadorRepositorio.getJugadorPorId(jugadorId);
    }

    public Jugador actualizarJugador(Long jugadorId, Jugador jugadorActualizar) {
        if (jugadorId == null) {
            log.info("Error. Jugador a actualizar no existe.");
            throw new RecursoNoEncontrado("No se encontró el jugador a actualizar. Favor de intentar de nuevo.");
        }
        if (jugadorActualizar.getNombreCompleto().isEmpty()){
            log.info("Error. Nombre de jugador a actualizar no existe.");
            throw new RecursoNoEncontrado("No se designó nombre completo para el jugador a actualizar. Favor de intentar de nuevo.");
        }
        if (jugadorActualizar.getElo() <= 0){
            log.info("Error. ELO de jugador a actualizar inválido.");
            throw new IllegalArgumentException("El puntaje ELO ingresado es menor o igual a 0. Favor de intentar de nuevo.");
        }
        log.info("El jugador {} ha sido actualizado exitosamente.", jugadorActualizar.getNombreCompleto());
        return jugadorRepositorio.actualizarJugador(jugadorId, jugadorActualizar);
    }

    public boolean borrarJugador(Long jugadorId) {
        Jugador jugador = getJugador(jugadorId);

        boolean jugadorPartidasActivas = partidaRepositorio.getPartidas().stream().anyMatch
                (partida -> (partida.getJugadorBlancasId().equals(jugadorId) || partida.getJugadorNegrasId().equals(jugadorId))
                        && partida.getEstado() != EstadoPartidaEnum.FINALIZADA);

        if (jugadorPartidasActivas) {
            log.info("El jugador {} tiene partidas activas. Favor de intentar de nuevo.", jugadorId);
            throw new PartidaActiva("El jugador seleccionado tiene partidas activas, por lo que no puede ser eliminado.");
        }

        log.info("El jugador {} ha sido eliminado exitosamente.", jugador.getNombreCompleto());
        return jugadorRepositorio.eliminarJugador(jugadorId);
    }

}
