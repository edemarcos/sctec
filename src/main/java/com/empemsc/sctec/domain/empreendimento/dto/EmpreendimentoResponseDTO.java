package com.empemsc.sctec.domain.empreendimento.dto;

public record EmpreendimentoResponseDTO(
        Long id,
        String nomeEmpreendimento,
        String nomeEmpreendedor,
        String municipio,
        String segmentoAtuacao,
        String email,
        String status
) {
}