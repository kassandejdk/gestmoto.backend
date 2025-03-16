package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmClient;
import com.yandoama.gestmoto.entity.enums.EStatut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GmClientRepository extends JpaRepository<GmClient, String> {
    /**
     * Permit to return a set of customer.
     * @param eStatut
     * @return List of customer
     */
    List<GmClient> findByStatutAndEntrepriseId(EStatut eStatut, String idEntreprise);
}
