package io.github.iodar.rest.v1.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Hello World Rest Controller")
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String greetingPath = "/greeting";
    @Value("${spring-app.greeting.value}")
    private String value;

    @DisplayName("sollte mit simplem String antworten wenn Aufruf ohne 'json' param")
    @Test
    void helloWorldWithoutJson_ShouldReturnSimpleString() throws Exception {
        // Act
        final ResultActions result = mockMvc.perform(get(greetingPath));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().string(is(equalTo(value + " from Spring"))));
    }

    @DisplayName("sollte mit Json-String antworten wenn Aufruf mit 'json=true' param")
    @Test
    void helloWorldWithJsonParam_ShouldReturnGreetingAsJson() throws Exception {
        // Act
        final ResultActions result = mockMvc.perform(get(greetingPath).param("json", "true"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().string(is(equalTo("{\"greeting\":\"" + value + " from Spring\"}"))));
    }

    @DisplayName("sollte mit Json-String antworten wenn Aufruf mit 'json=1' param")
    @Test
    void helloWorldWithJsonParam_UponCallingWithIntegerValue_ShouldReturnGreetingAsJson() throws Exception {
        // Act
        final ResultActions result = mockMvc.perform(get(greetingPath).param("json", "1"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().string(is(equalTo("{\"greeting\":\""+ value + " from Spring\"}"))));
    }
}
