package com.TorneoAjedrezAPI.modelo;

import lombok.Data;
@Data
public class Partida {

    private String apertura;
    private int numJugadas;
    private String resultado;
    private int tiempoTotal;
    private RitmoPartidaEnum ritmo;
    private EstadoPartidaEnum estado;
    private int puntajeBlancas;
    private int puntajeNegras;

    public Partida(RitmoPartidaEnum ritmo, EstadoPartidaEnum estado, String apertura, int numJugadas, String resultado, int tiempoTotal, int puntajeBlancas, int puntajeNegras) {
        this.ritmo = ritmo;
        this.estado = estado;
        this.apertura = apertura;
        this.numJugadas = numJugadas;
        this.resultado = resultado;
        this.tiempoTotal = tiempoTotal;
        this.puntajeBlancas = puntajeBlancas;
        this.puntajeNegras = puntajeNegras;
    }

    private Long partidaId;
    private Long jugadorBlancasId;
    private Long jugadorNegrasId;
}
