package com.empemsc.sctec.domain.socio.entity;

import com.empemsc.sctec.domain.empreendimento.entity.Empreendimento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private String qualificacao; // Ex: Sócio-Administrador, Sócio-Investidor

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empreendimento_id", nullable = false)
    @JsonIgnore // Essencial para evitar loop de serialização JSON
    private Empreendimento empreendimento;

}