package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmModeleDto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmModeleRespository;
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

public class GmModeleServiceTest extends AbstractTest {

    @Autowired
    private GmModeleRespository modeleRespository;

    /**
     * Retourne une liste de modèles.
     */
    @Test
    @DisplayName("Test de récuperation des modèles.")
    public void testGetModeles() throws Exception {
        String idEntreprise = "entreprise1";
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.MODELE + "/entreprises" + "/" + idEntreprise)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test d'enregistrement d'un modèle.")
    public void testPostModele() throws Exception {
        String idEntreprise = "entreprise1";
        GmModeleDto dto = new GmModeleDto();
        dto.setStatut(EStatut.ACTIF);
        dto.setLibelle("MODELE11");
        dto.setIdEntreprise(idEntreprise);

        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.MODELE)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'un modèle.")
    public void testUpdateModele() throws Exception {

        String idEntreprise = "entreprise1";
        String id = "modele1";
        GmModeleDto dto = new GmModeleDto();
        dto.setId(id);
        dto.setStatut(EStatut.ACTIF);
        dto.setLibelle("MODELE1");
        dto.setIdEntreprise(idEntreprise);

        final long sizeBefore = this.modeleRespository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.MODELE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());

        final long sizeAfter = this.modeleRespository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }


    /**
     * Suppress a model.
     */
    @Test
    @DisplayName("Test de suppression d'un modèle.")
    public void testDeleteModele() throws Exception {
        String idEntreprise = "entreprise1";
        String id = "modele1";

        final long sizeBefore = this.modeleRespository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.MODELE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.modeleRespository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }
}
