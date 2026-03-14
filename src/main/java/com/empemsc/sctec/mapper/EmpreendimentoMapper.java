package com.empemsc.sctec.mapper;

import com.empemsc.sctec.Empreendimento;
import com.empemsc.sctec.dto.AtualizarEmpreendimentoDTO;
import com.empemsc.sctec.dto.CriarEmpreendimentoDTO;
import com.empemsc.sctec.dto.EmpreendimentoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmpreendimentoMapper {

    Empreendimento toEntity(CriarEmpreendimentoDTO dto);

    EmpreendimentoResponseDTO toResponseDTO(Empreendimento entity);

    void updateEntityFromDTO(AtualizarEmpreendimentoDTO dto, @MappingTarget Empreendimento entity);
}