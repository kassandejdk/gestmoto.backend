package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmClientDto;
import com.yandoama.gestmoto.dto.GmUserDto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmClientRepository;
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

public class GmClientServiceTest extends AbstractTest {

    @Autowired
    private GmClientRepository clientRepository;

    /**
     * Retourne une liste de clients.
     */
    @Test
    @DisplayName("Test de récuperation des clients.")
    public void testGetClients() throws Exception {
        String idEntreprise = "entreprise1";
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.CLIENT + "/entreprises" + "/" + idEntreprise)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test d'enregistrement d'un client.")
    public void testPostClient() throws Exception {
        String idEntreprise = "entreprise1";

        GmUserDto udto = new GmUserDto();
        udto.setStatut(EStatut.ACTIF);
        udto.setNom("SAWADOGY");
        udto.setPrenom("Thierry");
        udto.setEmail("sawa@gmail.com");
        udto.setIdEntreprise(idEntreprise);
        udto.setTelephone("+22611223344");
        udto.setPassword("thierry@saa");

        GmClientDto dto = new GmClientDto();
        dto.setStatut(EStatut.ACTIF);
        dto.setVille("Ouaga");
        dto.setProfession("Commercant");
        dto.setProvince("KADIOGO");
        dto.setIdEntreprise(idEntreprise);
        dto.setUserDto(udto);

        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.CLIENT)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'un client.")
    public void testUpdateClient() throws Exception {

        String idEntreprise = "entreprise1";
        String id = "client1";
        GmUserDto udto = new GmUserDto();
        udto.setStatut(EStatut.ACTIF);
        udto.setId("user1");
        udto.setNom("SAWADOGY");
        udto.setPrenom("Thierry");
        udto.setEmail("sawa@gmail.com");
        udto.setIdEntreprise(idEntreprise);
        udto.setTelephone("+22611223344");
        udto.setPassword("thierry@saa");

        GmClientDto dto = new GmClientDto();
        dto.setStatut(EStatut.ACTIF);
        dto.setVille("Ouaga");
        dto.setProfession("Commercant");
        dto.setProvince("KADIOGO");
        dto.setIdEntreprise(idEntreprise);
        dto.setUserDto(udto);
        dto.setIdUtilisateur("user1");

        final long sizeBefore = this.clientRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.CLIENT + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());

        final long sizeAfter = this.clientRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }


    /**
     * Suppress a customer.
     */
    @Test
    @DisplayName("Test de suppression d'un client.")
    public void testDeleteClient() throws Exception {
        String idEntreprise = "entreprise1";
        String id = "client1";

        final long sizeBefore = this.clientRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.CLIENT + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.clientRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }
}
