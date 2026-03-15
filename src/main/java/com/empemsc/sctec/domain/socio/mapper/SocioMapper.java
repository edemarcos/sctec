package com.empemsc.sctec.domain.socio.mapper;

import com.empemsc.sctec.domain.socio.dto.CriarSocioDTO;
import com.empemsc.sctec.domain.socio.dto.SocioResponseDTO;
import com.empemsc.sctec.domain.socio.entity.Socio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocioMapper {

    Socio toEntity(CriarSocioDTO dto);

    SocioResponseDTO toResponseDTO(Socio entity);
}