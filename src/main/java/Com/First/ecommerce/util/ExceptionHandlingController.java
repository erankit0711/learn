package Com.First.ecommerce.util;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<CustomResponse<Object>> handleDataIntegrityViolationException(
        DataIntegrityViolationException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(CustomResponse.failure(null, exception.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<CustomResponse<Object>> handleNullPointerException(
        NullPointerException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(CustomResponse.failure(null, exception.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<CustomResponse<Object>> handleEntityNotFoundException(
        EntityNotFoundException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(CustomResponse.failure(null, exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<CustomResponse<Object>> handleException(
        Exception exception) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(CustomResponse.failure(null, exception.getMessage()));
    }
}
