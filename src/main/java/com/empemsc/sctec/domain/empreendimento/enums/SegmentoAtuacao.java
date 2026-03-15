package com.empemsc.sctec.domain.empreendimento.enums;

import lombok.Getter;

@Getter
public enum SegmentoAtuacao {

    TECNOLOGIA("Tecnologia"),
    COMERCIO("Comércio"),
    INDUSTRIA("Indústria"),
    SERVICOS("Serviços"),
    AGRONEGOCIO("Agronegócio");

    private final String descricao;

    SegmentoAtuacao(String descricao) {
        this.descricao = descricao;
    }

}