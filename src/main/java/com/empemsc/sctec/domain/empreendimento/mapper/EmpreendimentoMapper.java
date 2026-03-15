package com.empemsc.sctec.domain.empreendimento.mapper;

import com.empemsc.sctec.domain.empreendimento.entity.Empreendimento;
import com.empemsc.sctec.domain.empreendimento.dto.AtualizarEmpreendimentoDTO;
import com.empemsc.sctec.domain.empreendimento.dto.CriarEmpreendimentoDTO;
import com.empemsc.sctec.domain.empreendimento.dto.EmpreendimentoResponseDTO;
import com.empemsc.sctec.domain.socio.mapper.SocioMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {SocioMapper.class})
public interface EmpreendimentoMapper {

    Empreendimento toEntity(CriarEmpreendimentoDTO dto);

    EmpreendimentoResponseDTO toResponseDTO(Empreendimento entity);

    void updateEntityFromDTO(AtualizarEmpreendimentoDTO dto, @MappingTarget Empreendimento entity);
}