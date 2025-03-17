package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmPosteDto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmPosteRepository;
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

public class GmPosteServiceTest extends AbstractTest {

    @Autowired
    private GmPosteRepository posteRepository;

    /**
     * Retourne une liste de postes.
     */
    @Test
    @DisplayName("Test de récuperation des postes.")
    public void testGetGenres() throws Exception {
        String idEntreprise = "entreprise1";
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.POSTE + "/entreprises" + "/" + idEntreprise)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test d'enregistrement d'un poste.")
    public void testPostPoste() throws Exception {
        String idEntreprise = "entreprise1";
        GmPosteDto dto = new GmPosteDto();
        dto.setStatut(EStatut.ACTIF);
        dto.setLibelle("PDG");
        dto.setIdEntreprise(idEntreprise);

        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.POSTE)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'un poste.")
    public void testUpdatePoste() throws Exception {

        String idEntreprise = "entreprise1";
        String id = "poste1";
        GmPosteDto dto = new GmPosteDto();
        dto.setId(id);
        dto.setLibelle("Caissier");
        dto.setStatut(EStatut.ACTIF);
        dto.setIdEntreprise(idEntreprise);

        final long sizeBefore = this.posteRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.POSTE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());

        final long sizeAfter = this.posteRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }


    /**
     * Suppress a work.
     */
    @Test
    @DisplayName("Test de suppression d'un poste.")
    public void testDeletePoste() throws Exception {
        String idEntreprise = "entreprise1";
        String id = "poste1";

        final long sizeBefore = this.posteRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.POSTE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.posteRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }
}
