package com.empemsc.sctec.domain.empreendimento.dto;

import com.empemsc.sctec.domain.empreendimento.enums.SegmentoAtuacao;
import com.empemsc.sctec.domain.empreendimento.enums.StatusEmpreendimento;
import com.empemsc.sctec.domain.socio.dto.SocioResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Representação dos dados de um empreendimento no sistema")
public record EmpreendimentoResponseDTO(
        @Schema(description = "Identificador único do empreendimento", example = "1")
        Long id,
        @Schema(description = "Razão social ou nome fantasia", example = "Inova Tech Soluções")
        String nomeEmpreendimento,
        @Schema(description = "Nome do responsável", example = "João da Silva")
        String nomeEmpreendedor,
        @Schema(description = "Cidade de localização", example = "Florianópolis")
        String municipio,
        @Schema(description = "Setor de atuação")
        SegmentoAtuacao segmentoAtuacao,
        @Schema(description = "E-mail de contato", example = "contato@inovatech.com.br")
        String email,
        @Schema(description = "Situação atual do empreendimento")
        StatusEmpreendimento status,
        @Schema(description = "Data e hora do registro inicial no banco de dados", example = "2024-03-14T10:30:00")
        LocalDateTime dataCriacao,
        @Schema(description = "Data e hora da última modificação", example = "2024-03-14T12:00:00")
        LocalDateTime dataAtualizacao,
        @Schema(description = "Lista dos sócios vinculados a este empreendimento")
        List<SocioResponseDTO> socios
) {
}