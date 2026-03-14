package com.empemsc.sctec.dto;

public record AtualizarEmpreendimentoDTO(
        String nomeEmpreendimento,
        String nomeEmpreendedor,
        String municipio,
        String segmentoAtuacao,
        String email,
        String status
) {
}
