package com.yandoama.gestmoto.repository;

import com.yandoama.gestmoto.entity.GmUser;
import com.yandoama.gestmoto.entity.enums.EStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GmUserRepository extends JpaRepository<GmUser, String> {

    /**
     * Permet de retourner une liste d'users.
     * @param eStatut
     * @return List of User
     */
    List<GmUser> findByStatutAndIdEntreprise(EStatut eStatut, String idEntreprise);

    /**
     * Requete JPQL pour eviter les doublons.
     * @param id
     * @param nom
     * @param prenom
     * @param telephone
     * @param statut
     * @param idEntreprise
     * @return Boolean
     */
    @Query(
            "SELECT COUNT(*)> 0 FROM GmUser u"
                    + " WHERE u.entreprise.id = :idEntreprise "
                    + " AND u.nom = :nom"
                    + " AND u.prenom = :prenom"
                    + " AND u.statut = :statut"
                    + " AND ("
                    + "     (:id IS NULL AND u.telephone = :telephone)"
                    + "     OR (:id IS NOT NULL AND :id != u.id AND u.telephone = :telephone)"
                    + " )"
    )
    Boolean checkDuplicateUser(@Param("id") String id, @Param("nom") String nom, @Param("prenom") String prenom,
                               @Param("statut") EStatut statut,
                               @Param("idEntreprise") String idEntreprise, @Param("telephone") String telephone) ;
}
