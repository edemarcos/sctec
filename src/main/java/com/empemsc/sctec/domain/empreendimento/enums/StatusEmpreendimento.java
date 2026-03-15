package com.empemsc.sctec.domain.empreendimento.enums;

public enum StatusEmpreendimento {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    StatusEmpreendimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
