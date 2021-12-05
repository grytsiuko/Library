package ukma.hrytsiuk.library.errors;

import lombok.*;

import java.io.Serializable;

@Getter
public class MvcException extends RuntimeException implements Serializable {

    private final Integer code;
    private final String message;

    public MvcException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
