package com.empemsc.sctec.domain.socio.service;

import com.empemsc.sctec.domain.empreendimento.repository.EmpreendimentoRepository;
import com.empemsc.sctec.domain.socio.dto.CriarSocioDTO;
import com.empemsc.sctec.domain.socio.dto.SocioResponseDTO;
import com.empemsc.sctec.domain.socio.entity.Socio;
import com.empemsc.sctec.domain.socio.mapper.SocioMapper;
import com.empemsc.sctec.domain.socio.repository.SocioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SocioService {

    private final SocioRepository socioRepository;
    private final EmpreendimentoRepository empreendimentoRepository;
    private final SocioMapper socioMapper;

    public SocioService(SocioRepository socioRepository, EmpreendimentoRepository empreendimentoRepository, SocioMapper socioMapper) {
        this.socioRepository = socioRepository;
        this.empreendimentoRepository = empreendimentoRepository;
        this.socioMapper = socioMapper;
    }

    @Transactional
    public Optional<SocioResponseDTO> adicionarSocio(Long empreendimentoId, CriarSocioDTO dto) {
        return empreendimentoRepository.findById(empreendimentoId).map(empreendimento -> {
            Socio novoSocio = socioMapper.toEntity(dto);
            novoSocio.setEmpreendimento(empreendimento);
            Socio socioSalvo = socioRepository.save(novoSocio);
            return socioMapper.toResponseDTO(socioSalvo);
        });
    }

    @Transactional(readOnly = true)
    public List<SocioResponseDTO> listarSocios(Long empreendimentoId) {
        return socioRepository.findByEmpreendimentoId(empreendimentoId).stream()
                .map(socioMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public boolean removerSocio(Long empreendimentoId, Long socioId) {
        return socioRepository.findById(socioId)
                .filter(socio -> socio.getEmpreendimento().getId().equals(empreendimentoId))
                .map(socio -> { socioRepository.delete(socio); return true; })
                .orElse(false);
    }
}