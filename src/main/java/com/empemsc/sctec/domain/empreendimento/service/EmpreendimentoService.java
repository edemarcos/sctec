package com.empemsc.sctec.domain.empreendimento.service;

import com.empemsc.sctec.domain.empreendimento.entity.Empreendimento;
import com.empemsc.sctec.domain.empreendimento.dto.AtualizarEmpreendimentoDTO;
import com.empemsc.sctec.domain.empreendimento.dto.CriarEmpreendimentoDTO;
import com.empemsc.sctec.domain.empreendimento.dto.EmpreendimentoResponseDTO;
import com.empemsc.sctec.domain.empreendimento.enums.StatusEmpreendimento;
import com.empemsc.sctec.domain.empreendimento.mapper.EmpreendimentoMapper;
import com.empemsc.sctec.domain.empreendimento.repository.EmpreendimentoRepository;
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
        if (empreendimento.getStatus() == null) {
            empreendimento.setStatus(StatusEmpreendimento.ATIVO);
        }
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
    public Optional<EmpreendimentoResponseDTO> ativar(Long id) {
        return repository.findById(id).map(empreendimento -> {
            empreendimento.setStatus(StatusEmpreendimento.ATIVO);
            return mapper.toResponseDTO(empreendimento);
        });
    }

    @Transactional
    public Optional<EmpreendimentoResponseDTO> inativar(Long id) {
        return repository.findById(id).map(empreendimento -> {
            empreendimento.setStatus(StatusEmpreendimento.INATIVO);
            return mapper.toResponseDTO(empreendimento);
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