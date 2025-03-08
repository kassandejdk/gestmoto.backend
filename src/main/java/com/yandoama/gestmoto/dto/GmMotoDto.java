package com.yandoama.gestmoto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GmMotoDto {

    private String id;

    private String libelle;

    private String prix;

    private LocalDate date;

    private String marque;

    private String type;

    private String couleur;

    private String etat;

    private String immatriculation;

    private String numeroSerie;

    private String nbrePlace;

    private String sourceEnergie;

    private Float capacite;

    private String carosserie;

    private Float poidsVide;

    private String contactPrevenir;

    private Float chargeUtile;

    private String idGenre;

    private String idMoto;

    private String idModele;

    private String idEntreprise;

    private String idFournisseur;


}
