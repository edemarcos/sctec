package com.empemsc.sctec.domain.empreendimento.dto;

import com.empemsc.sctec.domain.empreendimento.enums.SegmentoAtuacao;
import com.empemsc.sctec.domain.empreendimento.enums.StatusEmpreendimento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para cadastro de um novo empreendimento")
public record CriarEmpreendimentoDTO(

        @NotBlank
        @Schema(description = "Razão social ou nome fantasia do empreendimento", example = "Inova Tech Soluções")
        String nomeEmpreendimento,
        @NotBlank
        @Schema(description = "Nome do responsável ou fundador", example = "João da Silva")
        String nomeEmpreendedor,
        @Schema(description = "Cidade onde a empresa está localizada", example = "Florianópolis")
        String municipio,
        @Schema(description = "Setor de atuação da empresa")
        SegmentoAtuacao segmentoAtuacao,
        @Email
        @Schema(description = "E-mail de contato principal", example = "contato@inovatech.com.br")
        String email,
        @Schema(description = "Status inicial do empreendimento no sistema")
        StatusEmpreendimento status
) {
}