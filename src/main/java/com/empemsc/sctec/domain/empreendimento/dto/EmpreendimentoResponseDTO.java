package com.empemsc.sctec.domain.empreendimento.dto;

import com.empemsc.sctec.domain.empreendimento.enums.SegmentoAtuacao;
import com.empemsc.sctec.domain.empreendimento.enums.StatusEmpreendimento;
import com.empemsc.sctec.domain.socio.dto.SocioResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record EmpreendimentoResponseDTO(
        Long id,
        String nomeEmpreendimento,
        String nomeEmpreendedor,
        String municipio,
        SegmentoAtuacao segmentoAtuacao,
        String email,
        StatusEmpreendimento status,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao,
        List<SocioResponseDTO> socios
) {
}