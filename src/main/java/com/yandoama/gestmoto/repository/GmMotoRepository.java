package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmMoto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GmMotoRepository extends JpaRepository<GmMoto, String> {

    /**
     * Requete JPQL pour eviter les doublons.
     *
     * @param id
     * @param numeroSerie
     * @param statut
     * @param idEntreprise
     * @return Boolean
     */
    @Query(
            "SELECT COUNT(*)> 0 FROM GmMoto g"
                    + " WHERE g.entreprise.id = :idEntreprise "
                    + " AND g.statut = :statut"
                    + " AND ("
                    + "     (:id IS NULL AND g.numeroSerie = :numeroSerie)"
                    + "     OR (:id IS NOT NULL AND :id != g.id AND g.numeroSerie = :numeroSerie)"
                    + " )"
    )
    Boolean checkDuplicateMoto(@Param("id") String id, @Param("numeroSerie") String numeroSerie,
                               @Param("statut") EStatut statut, @Param("idEntreprise") String idEntreprise);

    /**
     * Get a list of motorbike.
     *
     * @param statut
     * @param idEntreprise
     * @return List of motorbike
     */
    List<GmMoto> findByStatutAndEntrepriseId(EStatut statut, String idEntreprise);
}
