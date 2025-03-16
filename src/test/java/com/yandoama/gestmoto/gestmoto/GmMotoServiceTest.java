package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmMotoDto;
import com.yandoama.gestmoto.entity.enums.EEtat;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmMotoRepository;
import com.yandoama.gestmoto.utils.GmConstants;
import com.yandoama.gestmoto.utils.GmUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GmMotoServiceTest extends AbstractTest {

    @Autowired
    private GmMotoRepository motoRepository;

    /**
     * Return a list of motorbike.
     */
    @Test
    @DisplayName("Test de récuperation des motos.")
    public void testGetMotos() throws Exception {
        String idEntreprise = "entreprise1";
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.MOTO + "/entreprises" + "/" + idEntreprise)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Save a motorbike information.
     */
    @Test
    @DisplayName("Test d'enregistrement d'une moto.")
    public void testPostMoto() throws Exception {
        String idMoto = "moto11";
        String idModele = "modele1";
        String idGenre = "genre1";
        String idEntreprise = "entreprise1";
        GmMotoDto dto = new GmMotoDto();
        dto.setId(idMoto);
        dto.setStatut(EStatut.ACTIF);
        dto.setIdEntreprise(idEntreprise);
        dto.setEtat(EEtat.NEUF);
        dto.setNbrePlace(2);
        dto.setNumeroSerie("OJY201120");
        dto.setContactPrevenir("+22611223344");
        dto.setCouleur("blanc");
        dto.setImmatriculation("immar-cb11");
        dto.setDate(LocalDate.now());
        dto.setCarosserie("MOTO SOLO");
        dto.setMarque("YAMAHA");
        dto.setPoidsVide(122.0F);
        dto.setSourceEnergie("ESSENCE");
        dto.setType("110");
        dto.setIdFournisseur("fournisseur1");
        dto.setIdModele(idModele);
        dto.setIdGenre(idGenre);
        dto.setCapacite(455F);
        dto.setPrix(450000F);
        dto.setChargeUtile(345F);

        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.MOTO)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'une moto.")
    public void testUpdateMoto() throws Exception {
        String idMoto = "moto1";
        String idModele = "modele1";
        String idGenre = "genre1";
        String idEntreprise = "entreprise1";
        GmMotoDto dto = new GmMotoDto();
        dto.setId(idMoto);
        dto.setStatut(EStatut.ACTIF);
        dto.setIdEntreprise(idEntreprise);
        dto.setEtat(EEtat.NEUF);
        dto.setNbrePlace(2);
        dto.setNumeroSerie("OJY201120");
        dto.setContactPrevenir("+22611223344");
        dto.setCouleur("blanc");
        dto.setImmatriculation("immar-cb11");
        dto.setDate(LocalDate.now());
        dto.setCarosserie("MOTO SOLO");
        dto.setMarque("YAMAHA");
        dto.setPoidsVide(122.0F);
        dto.setSourceEnergie("ESSENCE");
        dto.setType("110");
        dto.setIdFournisseur("fournisseur1");
        dto.setIdModele(idModele);
        dto.setIdGenre(idGenre);
        dto.setCapacite(455F);

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.MOTO + "/" + idMoto)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }


    /**
     * Suppression une moto.
     */
    @Test
    @DisplayName("Test de suppression d'une moto.")
    public void testDeleteMoto() throws Exception {
        String id = "moto1";
        String idEntreprise = "entreprise1";

        final long sizeBefore = this.motoRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.MOTO + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.motoRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }
}
