package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmFacture;
import com.yandoama.gestmoto.entity.enums.EStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GmFactureRepository extends JpaRepository<GmFacture, String> {
    /**
     * Get a list of invoices information.
     * @param idEntreprise
     * @return List of Invoice
     */
    List<GmFacture> findByStatutAndEntrepriseId(EStatut statut, String idEntreprise);
}
