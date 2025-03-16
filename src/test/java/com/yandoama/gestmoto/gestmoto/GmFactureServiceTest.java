package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmFactureDto;
import com.yandoama.gestmoto.dto.GmFournisseurDto;
import com.yandoama.gestmoto.dto.GmUserDto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmFactureRepository;
import com.yandoama.gestmoto.utils.GmConstants;
import com.yandoama.gestmoto.utils.GmUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GmFactureServiceTest extends AbstractTest{



    @Autowired
    private GmFactureRepository factureRepository;

    /**
     * Retourne une liste de factutres.
     */
    @Test
    @DisplayName("Test de récuperation des factures.")
    public void testGetFactures() throws Exception {
        String idEntreprise = "entreprise1";
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.FACTURE + "/entreprises" + "/" + idEntreprise)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test d'enregistrement d'une facture.")
    public void testPostFacture() throws Exception {
        String idEntreprise = "entreprise1";
        GmFactureDto dto = new GmFactureDto();
        dto.setIdClient("client1");
        dto.setIdMoto("moto1");
        dto.setIdEntreprise(idEntreprise);

        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.FACTURE)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'une facture.")
    public void testUpdateFacture() throws Exception {

        String idEntreprise = "entreprise1";
        String id = "facture1";

        GmFactureDto dto = new GmFactureDto();
        dto.setId(id);
        dto.setIdClient("client1");
        dto.setStatut(EStatut.ACTIF);
        dto.setIdMoto("moto1");
        dto.setIdEntreprise(idEntreprise);

        final long sizeBefore = this.factureRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.FACTURE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());

        final long sizeAfter = this.factureRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }


    /**
     * Suppress an invoice.
     */
    @Test
    @DisplayName("Test de suppression d'une fatcure.")
    public void testDeleteFacture() throws Exception {
        String idEntreprise = "entreprise1";
        String id = "facture1";

        final long sizeBefore = this.factureRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.FACTURE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.factureRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }

}
