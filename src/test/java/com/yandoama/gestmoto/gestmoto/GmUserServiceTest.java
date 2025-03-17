package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmUserDto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmUserRepository;
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

public class GmUserServiceTest extends AbstractTest {
    @Autowired
    private GmUserRepository userRepository;

    /**
     * Retourne une liste de utilisateurs.
     */
    @Test
    @DisplayName("Test de récuperation des utilisateurs.")
    public void testGetUsers() throws Exception {
        String idEntreprise = "entreprise1";
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.USER + "/entreprises" + "/" + idEntreprise)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test d'enregistrement d'un utilisateur.")
    public void testPostUser() throws Exception {
        String idEntreprise = "entreprise1";
        GmUserDto dto = new GmUserDto();
        dto.setStatut(EStatut.ACTIF);
        dto.setNom("OUEDRAOGO");
        dto.setPrenom("Moussa");
        dto.setEmail("moussa@gmail.com");
        dto.setIdEntreprise(idEntreprise);
        dto.setTelephone("+22611223344");
        dto.setPassword("password");


        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.USER)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'un utilisateur.")
    public void testUpdateUser() throws Exception {

        String idEntreprise = "entreprise1";
        String id = "user1";
        GmUserDto dto = new GmUserDto();
        dto.setId(id);
        dto.setStatut(EStatut.ACTIF);
        dto.setNom("OUEDRAOGO");
        dto.setPrenom("Moussa");
        dto.setEmail("moussa@gmail.com");
        dto.setIdEntreprise(idEntreprise);
        dto.setTelephone("+22611223344");
        dto.setPassword("password");

        final long sizeBefore = this.userRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.USER + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());

        final long sizeAfter = this.userRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }


    /**
     * Suppress an user.
     */
    @Test
    @DisplayName("Test de suppression d'un utilisateur.")
    public void testDeleteUser() throws Exception {
        String idEntreprise = "entreprise1";
        String id = "user1";

        final long sizeBefore = this.userRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.USER + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.userRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }
}
