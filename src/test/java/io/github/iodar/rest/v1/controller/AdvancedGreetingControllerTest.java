package io.github.iodar.rest.v1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.iodar.rest.v1.dto.GreetingDto;
import io.github.iodar.service.core.GreetingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.inject.Inject;

import static java.time.LocalDateTime.now;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Erweiterter Greeting Controller")
class AdvancedGreetingControllerTest {

    @Inject
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @Value("${spring-app.advanced-greeting.value}")
    private String value;

    @Inject
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("sollte Greeting als Json liefern wenn Aufruf ohne Parameter")
    void getGreetingWithoutParamAndJson_ShouldReturnGreetingAsJson() throws Exception {
        // Assign
        final GreetingDto greetingDto = new GreetingDto()
                .setGreeting("Hello")
                .setDateTime(now().toString());
        when(greetingService.getGreeting()).thenReturn(greetingDto);

        // Act
        final ResultActions result = mockMvc.perform(get("/advanced/greeting")
                                                        .accept(APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(content().json(toJson(greetingDto)));
    }

    private String toJson(Object modelKlasse) throws JsonProcessingException {
        return objectMapper.writeValueAsString(modelKlasse);
    }
}