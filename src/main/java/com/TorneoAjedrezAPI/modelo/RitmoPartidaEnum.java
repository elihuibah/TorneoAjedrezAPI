package com.TorneoAjedrezAPI.modelo;

import lombok.Getter;

@Getter
public enum RitmoPartidaEnum {
    BALA(0,3),
    BLITZ(3,10),
    RAPID(10,60),
    CLASSIC(60, Integer.MAX_VALUE);

    private final int minDuracionMinutos;
    private final int maxDuracionMinutos;

    RitmoPartidaEnum(int minDuracionMinutos, int maxDuracionMinutos) {
        this.minDuracionMinutos = minDuracionMinutos;
        this.maxDuracionMinutos = maxDuracionMinutos;
    }

}
