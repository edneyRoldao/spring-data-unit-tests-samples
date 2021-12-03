package com.edn.base.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "entidade_taxa")
public class EntidadeTaxa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long  idTipoTaxa;

    private String nome;

    private boolean active;

    public EntidadeTaxa(Long idTipoTaxa) {
        this.idTipoTaxa = idTipoTaxa;
    }

    public EntidadeTaxa(Long idTipoTaxa, String nome) {
        this.idTipoTaxa = idTipoTaxa;
        this.nome = nome;
    }

    public  EntidadeTaxa(Long idTipoTaxa, String nome, boolean active) {
        this.idTipoTaxa = idTipoTaxa;
        this.nome = nome;
        this.active = active;
    }

}
