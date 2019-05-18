package io.github.iodar.rest.v1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.iodar.DBCleanupService;
import io.github.iodar.TransactionlessTestEntityManager;
import io.github.iodar.persistence.entities.UserDbo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.inject.Inject;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User Controller")
class UserControllerITest {

    @Inject
    private MockMvc mockMvc;

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private TransactionlessTestEntityManager transactionlessTestEntityManager;

    @Inject
    private DBCleanupService dbCleanupService;

    @BeforeEach
    void setUp() {
        dbCleanupService.cleanupDb();
    }

    @Test
    @DisplayName("sollte 404 liefern, wenn User mit der übergebenen Id nicht existiert")
    void getUserById_ShouldReturn404WhenNotFound() throws Exception {
        // act
        final ResultActions result = mockMvc.perform(
                get("/users/{id}", "1")
        );

        // assert
        result.andExpect(status().isNotFound())
                .andExpect(content().string(isEmptyOrNullString()));

    }

    @Test
    @DisplayName("sollte 200 und Nutzer liefern, wenn User mit übergebener Id existiert")
    void getUserById_ShouldReturnUserWithId() throws Exception {
        // prep
        final UserDbo user = new UserDbo()
                .setNachname("Müller")
                .setVorname("Peter")
                .setGeburtsdatum(now());

        final UserDbo persistedUser = transactionlessTestEntityManager.persist(user);

        // act
        final ResultActions result = mockMvc.perform(
                get("/users/{id}", persistedUser.getId())
        );

        // assert
        result.andExpect(status().isOk())
                .andExpect(content().string(not(isEmptyOrNullString())));

    }

    @Test
    @DisplayName("sollte exakten Nutzer liefern, wenn Nachname und Vorname gegeben sind")
    void getUserByNachnameAndVornameGivenBothParamsShouldReturnExactUser() throws Exception {
        //prep
        final String vorname = "Peter Heinz";
        final String nachname = "Müller";
        final UserDbo user = UserDbo.builder()
                .vorname(vorname)
                .nachname(nachname)
                .geburtsdatum(of(1986, 1, 1))
                .build();
        final UserDbo persistedUser = transactionlessTestEntityManager.persistAndFlush(user);

        // act
        final ResultActions result = mockMvc.perform(
                get("/users")
                        .param("nachname", nachname)
                        .param("vorname", vorname)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // assert
        result.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(is(not(isEmptyOrNullString()))))
                .andExpect(jsonPath("$.users[0].id", is(persistedUser.getId().intValue())))
                .andExpect(jsonPath("$.users[0].vorname", is(user.getVorname())))
                .andExpect(jsonPath("$.users[0].name", is(user.getNachname())));
    }

    private <T> String toJson(T modelKlasse) throws JsonProcessingException {
        return objectMapper.writeValueAsString(modelKlasse);
    }
}