package com.empemsc.sctec.domain.empreendimento.entity;

import com.empemsc.sctec.domain.empreendimento.enums.SegmentoAtuacao;
import com.empemsc.sctec.domain.empreendimento.enums.StatusEmpreendimento;
import com.empemsc.sctec.domain.socio.entity.Socio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Empreendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEmpreendimento;
    private String nomeEmpreendedor;
    private String municipio;
    @Enumerated(EnumType.STRING)
    private SegmentoAtuacao segmentoAtuacao;
    private String email;
    @Enumerated(EnumType.STRING)
    private StatusEmpreendimento status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "empreendimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Socio> socios = new ArrayList<>();

}