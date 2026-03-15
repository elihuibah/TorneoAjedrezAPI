package com.TorneoAjedrezAPI.controlador;

import com.TorneoAjedrezAPI.modelo.Jugador;
import com.TorneoAjedrezAPI.servicio.JugadorServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
public class JugadorControlador {
    private final JugadorServicio jugadorServicio;

    public JugadorControlador(JugadorServicio jugadorServicio) {
        this.jugadorServicio = jugadorServicio;
    }

    @PostMapping
    public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador){
        Jugador nuevoJugador = jugadorServicio.crearJugador(jugador);
        return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Jugador>> obtenerJugadores(){
        return new ResponseEntity<>(jugadorServicio.getTodosJugadores(), HttpStatus.OK);
    }

    @GetMapping("{jugadorId}")
    public ResponseEntity<Jugador> getJugador(@PathVariable long jugadorId){
        Jugador jugador = jugadorServicio.getJugador(jugadorId);
        return new ResponseEntity<>(jugador, HttpStatus.OK);
    }

    @PutMapping("{jugadorId}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable long jugadorId, @RequestBody Jugador jugador){
        Jugador jugadorActualizado = jugadorServicio.actualizarJugador(jugadorId, jugador);
        return new ResponseEntity<>(jugadorActualizado, HttpStatus.OK);
    }

    @DeleteMapping("{jugadorId}")
    public ResponseEntity<String> eliminarJugador(@PathVariable long jugadorId){
        boolean jugadorEliminado = jugadorServicio.borrarJugador(jugadorId);
        return new ResponseEntity<>("El jugador " + jugadorEliminado, HttpStatus.OK);
    }

}
