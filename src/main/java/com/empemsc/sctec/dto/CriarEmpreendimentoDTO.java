package com.empemsc.sctec.dto;

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
        String status
) {
}