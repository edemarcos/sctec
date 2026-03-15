package com.empemsc.sctec.domain.empreendimento.dto;

import com.empemsc.sctec.domain.empreendimento.enums.StatusEmpreendimento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriarEmpreendimentoDTO(

        @NotBlank
        String nomeEmpreendimento,
        @NotBlank
        String nomeEmpreendedor,
        String municipio,
        String segmentoAtuacao,
        @Email
        String email,
        StatusEmpreendimento status
) {
}