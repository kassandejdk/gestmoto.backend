package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmFournisseurDto;
import com.yandoama.gestmoto.dto.GmUserDto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmFournisseurRepository;
import com.yandoama.gestmoto.utils.GmConstants;
import com.yandoama.gestmoto.utils.GmUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GmFournisseurServiceTest extends AbstractTest {

    @Autowired
    private GmFournisseurRepository fournisseurRepository;

    /**
     * Retourne une liste de fournisseurs.
     */
    @Test
    @DisplayName("Test de récuperation des fournisseurs.")
    public void testGetFournisseurs() throws Exception {
        String idEntreprise = "entreprise1";
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.FOURNISSEUR + "/entreprises" + "/" + idEntreprise)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test d'enregistrement d'un fournisseur.")
    public void testPostFournisseur() throws Exception {
        String idEntreprise = "entreprise1";

        GmUserDto udto = new GmUserDto();
        udto.setStatut(EStatut.ACTIF);
        udto.setNom("OUEDRAOGO");
        udto.setPrenom("Moussa");
        udto.setEmail("moussa@gmail.com");
        udto.setIdEntreprise(idEntreprise);
        udto.setTelephone("+22611223344");
        udto.setPassword("password");

        GmFournisseurDto dto = new GmFournisseurDto();
        dto.setStatut(EStatut.ACTIF);
        dto.setIdUtilisateur("user1");
        dto.setIdEntreprise(idEntreprise);
        dto.setUser(udto);

        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.FOURNISSEUR)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'un fournisseur.")
    public void testUpdateFournisseur() throws Exception {

        String idEntreprise = "entreprise1";
        String id = "fournisseur1";

        GmUserDto udto = new GmUserDto();
        udto.setStatut(EStatut.ACTIF);
        udto.setNom("OUEDRAOGO");
        udto.setPrenom("Moussa");
        udto.setEmail("moussa@gmail.com");
        udto.setIdEntreprise(idEntreprise);
        udto.setTelephone("+22611223344");
        udto.setPassword("password");

        GmFournisseurDto dto = new GmFournisseurDto();
        dto.setId(id);
        dto.setIdUtilisateur("user1");
        dto.setStatut(EStatut.ACTIF);
        dto.setIdEntreprise(idEntreprise);
        dto.setUser(udto);

        final long sizeBefore = this.fournisseurRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.FOURNISSEUR + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());

        final long sizeAfter = this.fournisseurRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }


    /**
     * Suppress a supplier.
     */
    @Test
    @DisplayName("Test de suppression d'un fournisseur.")
    public void testDeleteFournisseur() throws Exception {
        String idEntreprise = "entreprise1";
        String id = "fournisseur1";

        final long sizeBefore = this.fournisseurRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.FOURNISSEUR + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.fournisseurRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }
}
