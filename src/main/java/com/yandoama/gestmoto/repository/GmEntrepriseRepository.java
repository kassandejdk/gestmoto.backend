package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmEntreprise;
import com.yandoama.gestmoto.entity.enums.EStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GmEntrepriseRepository extends JpaRepository<GmEntreprise, String> {

    /**
     * Permet de retourner une entreprise sur la base de son nom.
     *
     * @param denomination
     * @return an enterprise
     */
    boolean existsByDenomination(String denomination);

    /**
     * Permet de retourner une entreprise sur la base de son ifu, rccm, denomination.
     *
     * @param ifu
     * @param rccm
     * @param denomination
     * @return an enterprise
     */
    boolean existsByIfuAndRccmAndDenominationAndStatut(String ifu, String rccm, String denomination,EStatut statut);

    /**
     * Permet de renvoyer toutes les entreprises.
     * @param eStatut
     * @return List of enterprise
     */
    List<GmEntreprise> findByStatut(EStatut eStatut);
}
