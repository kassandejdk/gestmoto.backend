package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmPoste;
import com.yandoama.gestmoto.entity.enums.EStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GmPosteRepository extends JpaRepository<GmPoste, String> {

    /**
     * Requete JPQL pour eviter les doublons.
     *
     * @param id
     * @param libelle
     * @param statut
     * @param idEntreprise
     * @return Boolean
     */
    @Query(
            "SELECT COUNT(*)> 0 FROM GmPoste g"
                    + " WHERE g.entreprise.id = :idEntreprise "
                    + " AND g.statut = :statut"
                    + " AND ("
                    + "     (:id IS NULL AND g.libelle = :libelle)"
                    + "     OR (:id IS NOT NULL AND :id != g.id AND g.libelle = :libelle)"
                    + " )"
    )
    Boolean checkDuplicatePoste(@Param("id") String id, @Param("libelle") String libelle, @Param("statut")
    EStatut statut, @Param("idEntreprise") String idEntreprise);

    /**
     * Get information.
     *
     * @param eStatut
     * @return List
     */
    List<GmPoste> findByStatut(EStatut eStatut);
}
