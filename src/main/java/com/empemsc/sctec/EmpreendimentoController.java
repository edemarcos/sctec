package com.empemsc.sctec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empreendimento")
@Tag(name = "Empreendimentos", description = "Endpoints para gerenciamento de empreendimentos")
public class EmpreendimentoController {

    @Autowired
    private EmpreendimentoRepository repository;

    @PostMapping
    @Operation(summary = "Criar empreendimento", description = "Cria um novo empreendimento no sistema")
    public Empreendimento salvar(@RequestBody Empreendimento empreendimento) {
        return repository.save(empreendimento);
    }

    @GetMapping
    @Operation(summary = "Listar empreendimentos", description = "Retorna uma lista com todos os empreendimentos cadastrados")
    public List<Empreendimento> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empreendimento por ID", description = "Retorna um empreendimento pelo seu ID")
    public Empreendimento buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar empreendimento", description = "Atualiza um empreendimento existente")
    public Empreendimento atualizar(@PathVariable Long id, @RequestBody Empreendimento empreendimento) {
        Empreendimento empreendimentoExistente = repository.findById(empreendimento.getId()).orElseThrow();
        empreendimentoExistente = empreendimento;
        return repository.save(empreendimentoExistente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir empreendimento", description = "Exclui um empreendimento pelo seu ID")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
