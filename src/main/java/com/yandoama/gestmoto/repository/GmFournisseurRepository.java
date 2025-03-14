package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmFournisseur;
import com.yandoama.gestmoto.entity.GmUser;
import com.yandoama.gestmoto.entity.enums.EStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GmFournisseurRepository extends JpaRepository<GmFournisseur, String> {
    /**
     * Get suppliers informations.
     * @param eStatut
     * @param idEntreprise
     * @return List of Supplier
     */
    List<GmFournisseur> findByStatutAndIdEntreprise(EStatut eStatut, String idEntreprise);
}
