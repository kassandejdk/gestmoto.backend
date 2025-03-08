package com.yandoama.gestmoto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "gm_moto")
public class GmMoto {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "prix")
    private Float prix;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "marque")
    private String marque;

    @Column(name = "type")
    private String type;

    @Column(name = "etat")
    private String etat;

    @Column(name = "couleur")
    private String couleur;

    @Column(name = "immatriculation")
    private String immatriculation;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "nbre_place")
    private Integer nbrePlace;

    @Column(name = "source_energie")
    private String sourceEnergie;

    @Column(name = "capacite")
    private Float capacite;

    @Column(name = "carosserie")
    private String carosserie;

    @Column(name = "poids_vide")
    private Float poidsVide;

    @Column(name = "contact_prevenir")
    private String contactPrevenir;

    @Column(name = "charge_utile")
    private Float chargeUtile;

    @ManyToOne
    @JoinColumn(name = "entreprise")
    private GmEntreprise entreprise;

    @ManyToOne
    @JoinColumn(name = "genre")
    private GmGenre genre;

    @ManyToOne
    @JoinColumn(name = "modele")
    private GmModele modele;

    @ManyToOne
    @JoinColumn(name = "fournisseur")
    private GmFournisseur fournisseur;
}
