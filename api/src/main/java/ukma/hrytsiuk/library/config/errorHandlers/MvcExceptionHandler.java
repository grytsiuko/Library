package ukma.hrytsiuk.library.config.errorHandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ukma.hrytsiuk.library.errors.ErrorMessage;
import ukma.hrytsiuk.library.errors.MvcException;

@ControllerAdvice
public class MvcExceptionHandler {


    @ExceptionHandler(MvcException.class)
    public ResponseEntity<ErrorMessage> handleMvcException(MvcException e) {
        return ResponseEntity
                .status(e.getCode())
                .body(new ErrorMessage(e.getMessage()));
    }
}
