package com.yandoama.gestmoto.gestmoto;

import com.yandoama.gestmoto.dto.GmGenreDto;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.repository.GmGenreRepository;
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

public class GmGenreServiceTest extends AbstractTest {

    @Autowired
    private GmGenreRepository genreRepository;

    /**
     * Retourne une liste de genres.
     */
    @Test
    @DisplayName("Test de récuperation des genres.")
    public void testGetGenres() throws Exception {
        String idEntreprise = "entreprise1";
        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.GENRE + "/entreprises"+ "/" + idEntreprise)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test d'enregistrement d'une genre.")
    public void testPostGenre() throws Exception {
        String idEntreprise = "entreprise1";
        GmGenreDto dto = new GmGenreDto();
        dto.setStatut(EStatut.ACTIF);
        dto.setLibelle("MOTOCYCLE11");
        dto.setIdEntreprise(idEntreprise);

        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.GENRE)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());
    }

    @Test
    @DisplayName("Test de modification d'une genre.")
    public void testUpdateGenre() throws Exception {

        String idEntreprise = "entreprise1";
        String id = "genre1";
        GmGenreDto dto = new GmGenreDto();
        dto.setId(id);
        dto.setStatut(EStatut.ACTIF);
        dto.setLibelle("MOTOCYCLE1");
        dto.setIdEntreprise(idEntreprise);

        final long sizeBefore = this.genreRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(put(GmConstants.URLS.BASE_URL + GmConstants.URLS.GENRE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.statut").isNotEmpty());

        final long sizeAfter = this.genreRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }


    /**
     * Suppress a gender.
     */
    @Test
    @DisplayName("Test de suppression d'une genre.")
    public void testDeleteGenre() throws Exception {
        String idEntreprise = "entreprise1";
        String id = "genre1";

        final long sizeBefore = this.genreRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();

        mvc.perform(delete(GmConstants.URLS.BASE_URL + GmConstants.URLS.GENRE + "/" + id)
                        .content(GmUtils.convertObjectToJsonBytes(""))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        final long sizeAfter = this.genreRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise).size();
        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
    }
}
