package io.github.iodar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.sql.DataSourceDefinition;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

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
