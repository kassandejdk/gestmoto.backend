package com.yandoama.gestmoto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "gm_employe")
public class GmEmploye {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "salaire")
    private Float salaire;

    @ManyToOne
    @JoinColumn(name = "entreprise")
    private GmEntreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "user")
    private GmUser user;

    @ManyToOne
    @JoinColumn(name = "poste")
    private GmPoste poste;
}
