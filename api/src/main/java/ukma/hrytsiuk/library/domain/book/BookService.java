package ukma.hrytsiuk.library.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukma.hrytsiuk.library.amqp.rpc.RPCBookServiceInterface;
import ukma.hrytsiuk.library.amqp.rpc.dto.BookCreateDto;
import ukma.hrytsiuk.library.domain.book.dto.BookResponseDto;
import ukma.hrytsiuk.library.amqp.rpc.exceptions.BookIsbnAlreadyExistsException;
import ukma.hrytsiuk.library.domain.book.exception.BookNotFoundException;
import ukma.hrytsiuk.library.db.entities.book.BookRepository;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookRedisRepository bookRedisRepository;
    private final RPCBookServiceInterface rpcBookServiceInterface;

    @Transactional
    public BookResponseDto getById(Integer id) {
        return bookRedisRepository
                .getById(id)
                .orElseGet(() -> {
                    var entity = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
                    var dto = BookResponseDto.fromEntity(entity);
                    bookRedisRepository.save(id, dto);
                    return dto;
                });
    }

    @Transactional
    public Integer addNewBook(BookCreateDto createDto) {
       var rpcResult = rpcBookServiceInterface.save(createDto);
       if (rpcResult.isSuccess()) {
           return rpcResult.getResponse();
       }
       throw rpcResult.getException();
    }
}
