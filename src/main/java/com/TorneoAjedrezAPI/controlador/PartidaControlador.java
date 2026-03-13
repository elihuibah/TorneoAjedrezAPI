package com.TorneoAjedrezAPI.controlador;

import com.TorneoAjedrezAPI.modelo.Jugador;
import com.TorneoAjedrezAPI.modelo.Partida;
import com.TorneoAjedrezAPI.servicio.PartidaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/partidas")

public class PartidaControlador {
    private final PartidaServicio partidaServicio;

    public PartidaControlador(PartidaServicio partidaServicio) {
        this.partidaServicio = partidaServicio;
    }

    @PostMapping
    public ResponseEntity<Partida> registrarPartida(@RequestBody Partida partida, Jugador jugadorBlancas, Jugador jugadorNegras) {
        Partida nuevaPartida = partidaServicio.crearPartida(partida, jugadorBlancas, jugadorNegras);
        return new ResponseEntity<>(nuevaPartida, HttpStatus.OK);
    }

    @PutMapping("{id}/finalizar")
    public ResponseEntity<Partida> finalizarPartida(@PathVariable Long id, @RequestBody Partida partida, String resultado, int tiempoTotal) {
        Partida partidaFinalizada = partidaServicio.finalizarPartida(partida, resultado, tiempoTotal);
        return new ResponseEntity<>(partidaFinalizada, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Partida> obtenerPartida(@PathVariable Long id){
        Partida partida = partidaServicio.getPartida(id);
        return new ResponseEntity<>(partida, HttpStatus.OK);
    }

}
