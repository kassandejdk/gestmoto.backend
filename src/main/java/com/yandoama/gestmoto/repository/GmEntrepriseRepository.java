package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;

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
    boolean existsByIfuAndRccmAndDenomination(String ifu, String rccm, String denomination);
}
