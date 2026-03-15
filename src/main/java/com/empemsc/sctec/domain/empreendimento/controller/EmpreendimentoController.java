package com.empemsc.sctec.domain.empreendimento.controller;

import com.empemsc.sctec.domain.empreendimento.dto.AtualizarEmpreendimentoDTO;
import com.empemsc.sctec.domain.empreendimento.dto.CriarEmpreendimentoDTO;
import com.empemsc.sctec.domain.empreendimento.dto.EmpreendimentoResponseDTO;
import com.empemsc.sctec.domain.empreendimento.service.EmpreendimentoService;
import com.empemsc.sctec.core.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empreendimento")
@Tag(name = "Empreendimentos", description = "Endpoints para gerenciamento de empreendimentos")
public class EmpreendimentoController {

    private final EmpreendimentoService service;

    public EmpreendimentoController(EmpreendimentoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar empreendimento", description = "Cria um novo empreendimento no sistema")
    public ResponseEntity<ApiResponse<EmpreendimentoResponseDTO>> salvar(@RequestBody @Valid CriarEmpreendimentoDTO dto) {
        EmpreendimentoResponseDTO salvo = service.salvar(dto);
        return new ResponseEntity<>(ApiResponse.success(salvo), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar empreendimentos", description = "Retorna uma lista com todos os empreendimentos cadastrados")
    public ResponseEntity<ApiResponse<List<EmpreendimentoResponseDTO>>> listar() {
        List<EmpreendimentoResponseDTO> empreendimentos = service.listar();
        return ResponseEntity.ok(ApiResponse.success(empreendimentos));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empreendimento por ID", description = "Retorna um empreendimento pelo seu ID")
    public ResponseEntity<ApiResponse<EmpreendimentoResponseDTO>> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto)))
                .orElse(new ResponseEntity<>(ApiResponse.error("Empreendimento não encontrado."), HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar empreendimento", description = "Atualiza um empreendimento existente")
    public ResponseEntity<ApiResponse<EmpreendimentoResponseDTO>> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarEmpreendimentoDTO dto) {
        return service.atualizar(id, dto)
                .map(updatedDto -> ResponseEntity.ok(ApiResponse.success(updatedDto)))
                .orElse(new ResponseEntity<>(ApiResponse.error("Empreendimento não encontrado para atualização."), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir empreendimento", description = "Exclui um empreendimento pelo seu ID")
    public ResponseEntity<ApiResponse<String>> excluir(@PathVariable Long id) {
        if (service.excluir(id)) {
            return ResponseEntity.ok(ApiResponse.success("Empreendimento com ID " + id + " foi excluído com sucesso."));
        }
        return new ResponseEntity<>(ApiResponse.error("Empreendimento não encontrado para exclusão."), HttpStatus.NOT_FOUND);
    }
}
