package com.empemsc.sctec.domain.empreendimento.dto;

import com.empemsc.sctec.domain.empreendimento.enums.StatusEmpreendimento;

public record AtualizarEmpreendimentoDTO(
        String nomeEmpreendimento,
        String nomeEmpreendedor,
        String municipio,
        String segmentoAtuacao,
        String email,
        StatusEmpreendimento status
) {
}
