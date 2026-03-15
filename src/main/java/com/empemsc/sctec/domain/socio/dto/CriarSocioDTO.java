package com.empemsc.sctec.domain.socio.dto;

import jakarta.validation.constraints.NotBlank;

public record CriarSocioDTO(
        @NotBlank(message = "O nome do sócio é obrigatório")
        String nome,
        @NotBlank(message = "O CPF do sócio é obrigatório")
        String cpf,
        @NotBlank(message = "A qualificação do sócio é obrigatória")
        String qualificacao
) {
}