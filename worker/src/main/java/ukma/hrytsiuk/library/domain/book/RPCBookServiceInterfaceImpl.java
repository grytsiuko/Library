package ukma.hrytsiuk.library.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukma.hrytsiuk.library.amqp.dto.AmqpResponse;
import ukma.hrytsiuk.library.amqp.rpc.RPCBookServiceInterface;
import ukma.hrytsiuk.library.amqp.rpc.dto.BookCreateDto;
import ukma.hrytsiuk.library.amqp.rpc.exceptions.BookIsbnAlreadyExistsException;
import ukma.hrytsiuk.library.db.entities.book.BookRepository;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;

@Service
@RequiredArgsConstructor
public class RPCBookServiceInterfaceImpl implements RPCBookServiceInterface {

    private final BookRepository bookRepository;

    @Override
    public AmqpResponse<Integer> save(BookCreateDto createDto) {
        try {
            if (bookRepository.existsByIsbnIgnoreCase(createDto.getIsbn())) {
                throw new BookIsbnAlreadyExistsException(createDto.getIsbn());
            }
            BookEntity bookEntity = createDto.toEntity();
            bookRepository.save(bookEntity);
            return AmqpResponse.success(bookEntity.getId());
        } catch (RuntimeException e) {
            return AmqpResponse.error(e);
        }
    }
}
