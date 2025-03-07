package com.yandoama.gestmoto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "gm_genre")
public class GmGenre {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "libelle")
    private String libelle;
}
