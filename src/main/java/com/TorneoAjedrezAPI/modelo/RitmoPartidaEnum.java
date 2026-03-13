package com.TorneoAjedrezAPI.modelo;

import lombok.Getter;

@Getter
public enum RitmoPartidaEnum {
    BALA(3),
    BLITZ(10),
    RAPID(60),
    CLASSIC(Integer.MAX_VALUE);

    private final int maxDuracionMinutos;

    RitmoPartidaEnum(int maxDuracionMinutos) {
        this.maxDuracionMinutos = maxDuracionMinutos;
    }

}
