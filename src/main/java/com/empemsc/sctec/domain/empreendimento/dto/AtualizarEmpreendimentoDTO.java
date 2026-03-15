package com.empemsc.sctec.domain.empreendimento.dto;

import com.empemsc.sctec.domain.empreendimento.enums.SegmentoAtuacao;
import com.empemsc.sctec.domain.empreendimento.enums.StatusEmpreendimento;

public record AtualizarEmpreendimentoDTO(
        String nomeEmpreendimento,
        String nomeEmpreendedor,
        String municipio,
        SegmentoAtuacao segmentoAtuacao,
        String email,
        StatusEmpreendimento status
) {
}
