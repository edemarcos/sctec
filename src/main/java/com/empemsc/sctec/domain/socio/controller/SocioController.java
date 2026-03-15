package com.empemsc.sctec.domain.socio.controller;

import com.empemsc.sctec.core.response.ApiResponse;
import com.empemsc.sctec.domain.socio.dto.CriarSocioDTO;
import com.empemsc.sctec.domain.socio.dto.SocioResponseDTO;
import com.empemsc.sctec.domain.socio.service.SocioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empreendimento/{empreendimentoId}/socios")
@Tag(name = "Sócios", description = "Endpoints para gerenciamento de sócios de um empreendimento")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @PostMapping
    @Operation(summary = "Adicionar um novo sócio a um empreendimento")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Sócio adicionado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Erro de validação (dados inválidos)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Empreendimento não encontrado")
    })
    public ResponseEntity<ApiResponse<SocioResponseDTO>> adicionarSocio(
            @Parameter(description = "ID do empreendimento", example = "1") @PathVariable Long empreendimentoId, 
            @RequestBody @Valid CriarSocioDTO dto) {
        return socioService.adicionarSocio(empreendimentoId, dto)
                .map(socio -> new ResponseEntity<>(ApiResponse.success(socio), HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(ApiResponse.error("Empreendimento não encontrado para adicionar sócio."), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @Operation(summary = "Listar todos os sócios de um empreendimento")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<ApiResponse<List<SocioResponseDTO>>> listarSocios(
            @Parameter(description = "ID do empreendimento", example = "1") @PathVariable Long empreendimentoId) {
        List<SocioResponseDTO> socios = socioService.listarSocios(empreendimentoId);
        return ResponseEntity.ok(ApiResponse.success(socios));
    }

    @DeleteMapping("/{socioId}")
    @Operation(summary = "Remover um sócio de um empreendimento")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Sócio removido com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Sócio ou Empreendimento não encontrado")
    })
    public ResponseEntity<ApiResponse<String>> removerSocio(
            @Parameter(description = "ID do empreendimento", example = "1") @PathVariable Long empreendimentoId, 
            @Parameter(description = "ID do sócio a ser removido", example = "1") @PathVariable Long socioId) {
        if (socioService.removerSocio(empreendimentoId, socioId)) {
            return ResponseEntity.ok(ApiResponse.success("Sócio removido com sucesso."));
        }
        return new ResponseEntity<>(ApiResponse.error("Sócio ou Empreendimento não encontrado."), HttpStatus.NOT_FOUND);
    }
}