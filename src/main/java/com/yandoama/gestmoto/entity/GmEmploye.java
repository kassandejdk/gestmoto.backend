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

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "gm_employe")
public class GmEmploye extends AbstractInfo{

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "salaire")
    private Float salaire;

    @ManyToOne
    @JoinColumn(name = "entreprise")
    private GmEntreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "utilisateur")
    private GmUser utilisateur;

    @ManyToOne
    @JoinColumn(name = "poste")
    private GmPoste poste;
}
