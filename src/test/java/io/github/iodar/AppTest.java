package io.github.iodar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Collections.singleton;

@DisplayName("Spring Context")
@SpringBootTest
class AppTest {

    @Test
    @DisplayName("sollte laden")
    void springContextLoads() {
        Assertions.assertAll(
                () -> App.main(singleton("").toArray(String[]::new))
        );
    }
}
