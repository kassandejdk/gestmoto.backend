package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmEmployeDto;
import com.yandoama.gestmoto.dto.GmUserDto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmEmployeRepository;
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

public class GmEmployeServiceTest extends AbstractTest {

    @Autowired
    private GmEmployeRepository employeRepository;

    /**
     * Retourne une liste d'employés.
     */
    @Test
    @DisplayName("Test de récuperation des employés.")
    public void testGetEmployes() throws Exception {
        String idEntreprise = "entreprise1";
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.EMPLOYE + "/entreprises" + "/" + idEntreprise)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Test d'enregistrement d'un employé.")
    public void testPostEmploye() throws Exception {
        String idEntreprise = "entreprise1";

        GmUserDto udto = new GmUserDto();
        udto.setStatut(EStatut.ACTIF);
        udto.setNom("OUEDRAOGO");
        udto.setPrenom("Moussa");
        udto.setEmail("moussa@gmail.com");
        udto.setIdEntreprise(idEntreprise);
        udto.setTelephone("+22611223344");
        udto.setPassword("password");

        GmEmployeDto dto = new GmEmployeDto();
        dto.setStatut(EStatut.ACTIF);
        dto.setIdPoste("poste1");
        dto.setSalaire(100000F);
        dto.setIdUtilisateur("user1");
        dto.setIdEntreprise(idEntreprise);
        dto.setUserDto(udto);

        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.EMPLOYE)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'un employé.")
    public void testUpdateEmploye() throws Exception {

        String idEntreprise = "entreprise1";
        String id = "employe1";

        GmUserDto udto = new GmUserDto();
        udto.setStatut(EStatut.ACTIF);
        udto.setNom("OUEDRAOGO");
        udto.setPrenom("Moussa");
        udto.setEmail("moussa@gmail.com");
        udto.setIdEntreprise(idEntreprise);
        udto.setTelephone("+22611223344");
        udto.setPassword("password");

        GmEmployeDto dto = new GmEmployeDto();
        dto.setStatut(EStatut.ACTIF);
        dto.setIdPoste("poste1");
        dto.setSalaire(150000F);
        dto.setIdUtilisateur("user1");
        dto.setIdEntreprise(idEntreprise);
        dto.setUserDto(udto);

        final long sizeBefore = this.employeRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.EMPLOYE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());

        final long sizeAfter = this.employeRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }


    /**
     * Suppress a employe.
     */
    @Test
    @DisplayName("Test de suppression d'un employé.")
    public void testDeleteEmploye() throws Exception {
        String idEntreprise = "entreprise1";
        String id = "employe1";

        final long sizeBefore = this.employeRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.EMPLOYE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.employeRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }
}
