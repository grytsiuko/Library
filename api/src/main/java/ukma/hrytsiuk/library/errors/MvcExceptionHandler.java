package ukma.hrytsiuk.library.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {


    @ExceptionHandler(MvcException.class)
    public ResponseEntity<ErrorMessage> handleMvcException(MvcException e) {
        return ResponseEntity
                .status(e.getCode())
                .body(new ErrorMessage(e.getMessage()));
    }
}
