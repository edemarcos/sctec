package com.empemsc.sctec.domain.empreendimento.repository;

import com.empemsc.sctec.domain.empreendimento.entity.Empreendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {
}
