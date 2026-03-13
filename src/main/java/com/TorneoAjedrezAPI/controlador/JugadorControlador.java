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
        return new ResponseEntity<>(jugadorServicio.getJugadores(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Jugador> getJugador(@PathVariable long id){
        Jugador jugador = jugadorServicio.getJugador(id);
        return new ResponseEntity<>(jugador, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable long id, @RequestBody Jugador jugador){
        Jugador jugadorActualizado = jugadorServicio.actualizarJugador(id, jugador);
        return new ResponseEntity<>(jugadorActualizado, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarJugador(@PathVariable long id){
        boolean jugadorEliminado = jugadorServicio.borrarJugador(id);
        return new ResponseEntity<>("El jugador " + jugadorEliminado, HttpStatus.OK);
    }

}
