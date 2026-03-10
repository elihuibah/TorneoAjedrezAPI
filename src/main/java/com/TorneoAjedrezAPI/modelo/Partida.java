package com.TorneoAjedrezAPI.modelo;

import lombok.Data;
@Data
public class Partida {

    private enum Ritmo {
        bala(3),
        blitz(10),
        valid(60),
        classic(Integer.MAX_VALUE);

        private final int maxDuracionMinutos;

        Ritmo(int maxDuracionMinutos) {
            this.maxDuracionMinutos = maxDuracionMinutos;
        }
    }

    private enum Estado {
        enProceso,
        pendiente,
        finalizada
    }

    private String apertura;
    private int numJugadas;
    private String resultado;
    private int tiempoTotal;
    private Ritmo ritmo;
    private Estado estado;

    public Partida(Ritmo ritmo, Estado estado, String apertura, int numJugadas, String resultado, int tiempoTotal) {
        this.ritmo = ritmo;
        this.estado = estado;
        this.apertura = apertura;
        this.numJugadas = numJugadas;
        this.resultado = resultado;
        this.tiempoTotal = tiempoTotal;
    }

    private Long partidaId;
    private Long jugadorBlancasId;
    private Long jugadorNegrasId;
}
