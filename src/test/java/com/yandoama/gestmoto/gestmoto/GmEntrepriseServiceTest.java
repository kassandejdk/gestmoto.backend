package com.yandoama.gestmoto.gestmoto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yandoama.gestmoto.dto.GmEntrepriseDto;
import com.yandoama.gestmoto.repository.GmEntrepriseRepository;
import com.yandoama.gestmoto.utils.GmConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
public class GmEntrepriseServiceTest {

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
    private static final ObjectMapper MAPPER = createObjectMapper();
    @Autowired
    protected MockMvc mvc;

    @Autowired
    private GmEntrepriseRepository entrepriseRepository;

    /**
     * Retourne une liste d'entreprise.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("Test de recuperation des entreprises.")
    public void testGetEntreprises() throws Exception {

        mvc.perform(get(GmConstants.URLS.BASE_URL + GmConstants.URLS.ENTREPRISE)
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


        mvc.perform(post(GmConstants.URLS.BASE_URL + GmConstants.URLS.ENTREPRISE)
                        .content(MAPPER.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
