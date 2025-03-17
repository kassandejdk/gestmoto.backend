package com.yandoama.gestmoto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gm_fournisseur")
public class GmFournisseur extends AbstractInfo{
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "entreprise")
    private GmEntreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "utilisateur")
    private GmUser utilisateur;
}
