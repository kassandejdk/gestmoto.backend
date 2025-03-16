package com.yandoama.gestmoto.gestmoto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Cette classe abstraite permet de definir les méthodes communes aux classes de test.
 * ExtendWith ( car j'utilise JUnit 5) + SpringBootTest: permet d'étendre l'execution des tests avec Spring et charge le contexte
 * entier de l'application(cela permet d'interagir avec l'application comme si elle
 * était déployée dans un environnement réel) pour effectuer les test.
 * <p>
 * AutoConfigureMockMvc : Permet de configurer un mock de `MockMvc` sans lancer un véritable serveur HTTP.
 * Cela permet de tester les controleurs HTTP sans démarrer une instance de serveur.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:clear-data.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:test-data.sql"),
})
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
public abstract class AbstractTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

}
