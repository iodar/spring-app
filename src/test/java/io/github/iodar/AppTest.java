package io.github.iodar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
@DisplayName("Spring App")
public class AppTest {

    @Test
    @DisplayName("sollte starten")
    void springTest() {
        App.main(new String[]{"null"});
    }
}
