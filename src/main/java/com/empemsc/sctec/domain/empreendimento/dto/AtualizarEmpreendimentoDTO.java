package com.empemsc.sctec.domain.empreendimento.dto;

import com.empemsc.sctec.domain.empreendimento.enums.SegmentoAtuacao;
import com.empemsc.sctec.domain.empreendimento.enums.StatusEmpreendimento;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para atualização parcial ou total de um empreendimento")
public record AtualizarEmpreendimentoDTO(
        @Schema(description = "Razão social ou nome fantasia do empreendimento", example = "Inova Tech Soluções Modernas")
        String nomeEmpreendimento,
        @Schema(description = "Nome do responsável ou fundador", example = "João da Silva Sauro")
        String nomeEmpreendedor,
        @Schema(description = "Cidade onde a empresa está localizada", example = "São José")
        String municipio,
        @Schema(description = "Setor de atuação da empresa")
        SegmentoAtuacao segmentoAtuacao,
        @Schema(description = "E-mail de contato principal", example = "contato_novo@inovatech.com.br")
        String email,
        @Schema(description = "Status do empreendimento no sistema")
        StatusEmpreendimento status
) {
}
