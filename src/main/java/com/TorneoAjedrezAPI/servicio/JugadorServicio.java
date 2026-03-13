package com.TorneoAjedrezAPI.servicio;

import com.TorneoAjedrezAPI.modelo.Jugador;
import com.TorneoAjedrezAPI.repositorio.JugadorRepositorio;
import com.TorneoAjedrezAPI.repositorio.PartidaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorServicio {

    private final JugadorRepositorio jugadorRepositorio;
    private final PartidaRepositorio partidaRepositorio;

    public JugadorServicio(JugadorRepositorio jugadorRepositorio, PartidaRepositorio partidaRepositorio) {
        this.jugadorRepositorio = jugadorRepositorio;
        this.partidaRepositorio = partidaRepositorio;
    }

    public Jugador crearJugador(Jugador jugador) {
        boolean nombreinValido = jugador.getNombreCompleto().isEmpty();
        boolean nacionalidadinValida =  jugador.getNacionalidad().isEmpty();

        if (nombreinValido || jugador.getNombreCompleto() == null || nacionalidadinValida) {
            throw new IllegalArgumentException("No se introdujo el nombre adecuadamente. Favor de intentar de nuevo.");
        }

        if(jugador.getElo() <= 0){
            throw new IllegalArgumentException("El puntaje ELO ingresado es menor o igual a 0. Favor de intentar de nuevo.");
        }

        return jugadorRepositorio.crearJugador(jugador);
    }

    public List<Jugador> getJugadores() {
        return jugadorRepositorio.getJugadores();
    }

    public Jugador getJugador(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del jugador a buscar no puede ser nulo. Favor de intentar de nuevo.");
        }

        return jugadorRepositorio.getJugadorPorId(id);
    }

    public Jugador actualizarJugador(Long id, Jugador jugadorActualizar) {
        if (id == null) {
            throw new IllegalArgumentException("No se encontró el jugador a actualizar. Favor de intentar de nuevo.");
        }
        if (jugadorActualizar.getNombreCompleto().isEmpty()){
            throw new IllegalArgumentException("No se designó nombre completo para el jugador a actualizar. Favor de intentar de nuevo.");
        }
        if (jugadorActualizar.getElo() <= 0){
            throw new IllegalArgumentException("El puntaje ELO ingresado es menor o igual a 0. Favor de intentar de nuevo.");
        }
        return jugadorRepositorio.actualizarJugador(id, jugadorActualizar);
    }

    public boolean borrarJugador(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("No se encontró el jugador a actualizar. Favor de intentar de nuevo.");
        }

        return jugadorRepositorio.eliminarJugador(id);
    }

}
