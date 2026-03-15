package com.empemsc.sctec.domain.socio.repository;

import com.empemsc.sctec.domain.socio.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    List<Socio> findByEmpreendimentoId(Long empreendimentoId);
}