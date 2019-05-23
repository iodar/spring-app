package io.github.iodar.rest.v1.exceptionhandling;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            BindException.class,
            DateTimeParseException.class
    })
    public ResponseEntity<FehlerDto> handlePutCommandException() {
        final FehlerDto fehlerDto = FehlerDto.builder()
                .fehler("Nicht alle Felder konnten verarbeitet werden")
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(fehlerDto.status)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(fehlerDto);
    }

    @Getter
    @Builder
    @JsonPropertyOrder({"status", "fehler"})
    private static class FehlerDto {
        private int status;
        private String fehler;
    }
}
