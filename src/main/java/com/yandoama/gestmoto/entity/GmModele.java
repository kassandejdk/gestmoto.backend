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
@Table(name = "gm_modele")
public class GmModele {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "libelle")
    private String libelle;
}
