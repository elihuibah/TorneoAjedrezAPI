package com.TorneoAjedrezAPI.modelo;

import lombok.Data;
@Data
public class Partida {

    private String ritmo; //dato 'Objeto'
    private String apertura;
    private int numJugadas;
    private String estado; //dato 'estado'
    private String resultado;
    private int tiempoTotal;

    public Partida(String ritmo, String apertura, int numJugadas, String estado, int tiempoTotal) {
        this.ritmo = ritmo;
        this.apertura = apertura;
        this.numJugadas = numJugadas;
        this.estado = estado;
        this.tiempoTotal = tiempoTotal;
    }

    private Long partidaId;
    private Long jugadorBlancasId;
    private Long jugadorNegrasId;
}
