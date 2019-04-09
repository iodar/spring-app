package io.github.iodar.rest.v1.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String greetingPath = "/greeting";

    @Test
    void helloWorldWithoutJson_ShouldReturnSimpleString() throws Exception {
        // Act
        final ResultActions result = mockMvc.perform(get(greetingPath));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().string(is(equalTo("Greetings from Spring"))));
    }

    @Test
    void helloWorldWithJsonParam_ShouldReturnGreetingAsJson() throws Exception {
        // Act
        final ResultActions result = mockMvc.perform(get(greetingPath).param("json", "true"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().string(is(equalTo("{\"greeting\":\"Greetings from Spring\"}"))));
    }

    @Test
    void helloWorldWithJsonParam_UponCallingWithIntegerValue_ShouldReturnGreetingAsJson() throws Exception {
        // Act
        final ResultActions result = mockMvc.perform(get(greetingPath).param("json", "1"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().string(is(equalTo("{\"greeting\":\"Greetings from Spring\"}"))));
    }
}
