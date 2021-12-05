package ukma.hrytsiuk.library.amqp.rpc;

import ukma.hrytsiuk.library.amqp.dto.AmqpResponse;
import ukma.hrytsiuk.library.amqp.rpc.dto.BookCreateDto;

public interface RPCBookServiceInterface {
    AmqpResponse<Integer> save(BookCreateDto createDto);
}
