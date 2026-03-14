package com.empemsc.sctec.service;

import com.empemsc.sctec.Empreendimento;
import com.empemsc.sctec.EmpreendimentoRepository;
import com.empemsc.sctec.dto.AtualizarEmpreendimentoDTO;
import com.empemsc.sctec.dto.CriarEmpreendimentoDTO;
import com.empemsc.sctec.dto.EmpreendimentoResponseDTO;
import com.empemsc.sctec.mapper.EmpreendimentoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpreendimentoService {

    private final EmpreendimentoRepository repository;
    private final EmpreendimentoMapper mapper;

    public EmpreendimentoService(EmpreendimentoRepository repository, EmpreendimentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public EmpreendimentoResponseDTO salvar(CriarEmpreendimentoDTO dto) {
        Empreendimento empreendimento = mapper.toEntity(dto);
        Empreendimento salvo = repository.save(empreendimento);
        return mapper.toResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<EmpreendimentoResponseDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<EmpreendimentoResponseDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO);
    }

    @Transactional
    public Optional<EmpreendimentoResponseDTO> atualizar(Long id, AtualizarEmpreendimentoDTO dto) {
        return repository.findById(id).map(empreendimentoExistente -> {
            mapper.updateEntityFromDTO(dto, empreendimentoExistente);
            return mapper.toResponseDTO(empreendimentoExistente);
        });
    }

    @Transactional
    public boolean excluir(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}