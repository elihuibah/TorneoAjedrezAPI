package com.TorneoAjedrezAPI.modelo;

import lombok.Data;

@Data
public class Jugador {

    private String nombreCompleto;
    private char genero;
    private int elo;
    private byte edad;
    private String nacionalidad;

    public Jugador(String nombreCompleto, char genero, int elo, byte edad, String nacionalidad) {
        this.nombreCompleto = nombreCompleto;
        this.genero = genero;
        this.elo = elo;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }

    private Long jugadorId;


}
