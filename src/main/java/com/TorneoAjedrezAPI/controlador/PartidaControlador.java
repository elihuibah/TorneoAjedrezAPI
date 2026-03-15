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

    @PostMapping("{partidaId}/finalizar")
    public ResponseEntity<Partida> finalizarPartida(@PathVariable Long partidaId, @RequestBody Partida partida, @RequestParam String resultado, @RequestParam int tiempoTotal) {
        Partida partidaFinalizada = partidaServicio.finalizarPartida(partidaId, resultado, tiempoTotal);
        return new ResponseEntity<>(partidaFinalizada, HttpStatus.OK);
    }

    @GetMapping("{partidaId}")
    public ResponseEntity<Partida> obtenerPartida(@PathVariable Long partidaId){
        Partida partida = partidaServicio.getPartida(partidaId);
        return new ResponseEntity<>(partida, HttpStatus.OK);
    }

}
