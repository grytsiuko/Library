package ukma.hrytsiuk.library.errors;

import lombok.*;

@Getter
public class MvcException extends RuntimeException {

    private final Integer code;
    private final String message;

    public MvcException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
