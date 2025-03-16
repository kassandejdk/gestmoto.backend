package com.yandoama.gestmoto.dto;

import com.yandoama.gestmoto.entity.enums.EEtat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GmMotoDto extends AbstractInfoDto{

    private String id;

    private Float prix;

    private LocalDate date;

    private String marque;

    private String type;

    private String couleur;

    private EEtat etat;

    private String immatriculation;

    private String numeroSerie;

    private Integer nbrePlace;

    private String sourceEnergie;

    private Float capacite;

    private String carosserie;

    private Float poidsVide;

    private String contactPrevenir;

    private Float chargeUtile;

    private String idGenre;

    private String idModele;

    private String idEntreprise;

    private String idFournisseur;


}
