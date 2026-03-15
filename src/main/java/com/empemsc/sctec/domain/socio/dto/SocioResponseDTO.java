package com.empemsc.sctec.domain.socio.dto;

public record SocioResponseDTO(
        Long id,
        String nome,
        String cpf,
        String qualificacao
) {
}