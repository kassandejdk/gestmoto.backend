package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmEntrepriseDto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmEntrepriseRepository;
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

public class GmEntrepriseServiceTest extends AbstractTest {

    @Autowired
    private GmEntrepriseRepository entrepriseRepository;

    /**
     * Retourne une liste d'entreprise.
     *
     */
    @Test
    @DisplayName("Test de récuperation des entreprises.")
    public void testGetEntreprises() throws Exception {
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.ENTREPRISE)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test d'enregistrement d'une entreprise.")
    public void testPostEntreprise() throws Exception {
        GmEntrepriseDto dto = new GmEntrepriseDto();
        dto.setDenomination("Test Entreprise");
        dto.setIfu("1234567890");
        dto.setRccm("1234567890");
        dto.setAdresse("Bobo-Dioulasso, secteur 29");
        dto.setTelephone("+22674415998");
        dto.setLogoUrl("https://logo.url");

        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.ENTREPRISE)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'une entreprise.")
    public void testUpdateEntreprise() throws Exception {

        String id = "entreprise1";
        GmEntrepriseDto dto = new GmEntrepriseDto();
        dto.setId(id);
        dto.setStatut(EStatut.ACTIF);
        dto.setDenomination("Entreprise modifier");
        dto.setIfu("1234567890");
        dto.setRccm("1234567890");
        dto.setAdresse("Bobo-Dioulasso, secteur 29");
        dto.setTelephone("+22674415998");
        dto.setLogoUrl("https://logo-modif.url");

        final long sizeBefore = this.entrepriseRepository.findByStatut(EStatut.ACTIF).size();

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.ENTREPRISE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());

        final long sizeAfter = this.entrepriseRepository.findByStatut(EStatut.ACTIF).size();
        Assertions.assertEquals(sizeBefore , sizeAfter);
    }


    /**
     * Suppression une entreprise.
     *
     */
    @Test
    @DisplayName("Test de suppression d'une entreprise.")
    public void testDeleteEntreprise() throws Exception {
        String id = "entreprise1";

        final long sizeBefore = this.entrepriseRepository.findByStatut(EStatut.ACTIF).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.ENTREPRISE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.entrepriseRepository.findByStatut(EStatut.ACTIF).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }

}
