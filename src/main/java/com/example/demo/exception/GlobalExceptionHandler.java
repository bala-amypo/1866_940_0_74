import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethod(MethodArgumentNotValidException ex) {
        // Extracting validation errors
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        
        // Creating a list of error messages from the field errors
        List<String> errors = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        // Returning a response entity with the errors
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
