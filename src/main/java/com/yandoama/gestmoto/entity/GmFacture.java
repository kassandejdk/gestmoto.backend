package com.yandoama.gestmoto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "gm_facture")
public class GmFacture {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "date")
    private LocalDateTime date;


}
