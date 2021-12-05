package ukma.hrytsiuk.library.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ukma.hrytsiuk.library.domain.book.dto.BookCreateDto;
import ukma.hrytsiuk.library.domain.book.dto.BookResponseDto;
import ukma.hrytsiuk.library.domain.book.exception.BookIsbnAlreadyExistsException;
import ukma.hrytsiuk.library.domain.book.exception.BookNotFoundException;
import ukma.hrytsiuk.library.db.entities.book.BookRepository;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookRedisRepository bookRedisRepository;

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
        if (bookRepository.existsByIsbnIgnoreCase(createDto.getIsbn())) {
            throw new BookIsbnAlreadyExistsException(createDto.getIsbn());
        }
        BookEntity bookEntity = createDto.toEntity();
        bookRepository.save(bookEntity);
        return bookEntity.getId();
    }
}
