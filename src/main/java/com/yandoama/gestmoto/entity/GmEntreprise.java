package com.yandoama.gestmoto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "gm_entreprise")
public class GmEntreprise {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "denomination")
    private String denomination;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "ifu")
    private String ifu;

    @Column(name = "rccm")
    private String rccm;
}
