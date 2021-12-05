package ukma.hrytsiuk.library.amqp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AmqpResponse<Response> {

    private boolean success;
    private Response response;
    private RuntimeException exception;

    public static <T> AmqpResponse<T> success(T response) {
        return new AmqpResponse<>(true, response, null);
    }

    public static <T> AmqpResponse<T> error(RuntimeException exception) {
        return new AmqpResponse<>(false, null, exception);
    }
}
