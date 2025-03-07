package com.yandoama.gestmoto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "gm_fournisseur")
public class GmFournisseur {
    @Id
    @Column(name = "id")
    private String id;
}
