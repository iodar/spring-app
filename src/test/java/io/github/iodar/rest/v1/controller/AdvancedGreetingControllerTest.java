package io.github.iodar.rest.v1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.iodar.rest.v1.dto.GreetingDto;
import io.github.iodar.service.core.GreetingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DisplayName("Erweiterter Greeting Controller")
class AdvancedGreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @Value("${spring-app.advanced-greeting.value}")
    private String value;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("sollte Greeting als Json liefern wenn Aufruf ohne Parameter")
    void getGreetingWithoutParamAndJson_ShouldReturnGreetingAsJson() throws Exception {
        // Assign
        final GreetingDto expectedDto = new GreetingDto()
                .setGreeting("Hello")
                .setDateTime(now().toString());
        when(greetingService.getGreeting()).thenReturn(expectedDto);

        // Act
        final ResultActions result = mockMvc.perform(get("/advanced/greeting")
                                                        .accept(APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(content().json(toJson(expectedDto)))
                .andDo(print());
    }

    private <T> String toJson(T modelKlasse) throws JsonProcessingException {
        return objectMapper.writeValueAsString(modelKlasse);
    }
}