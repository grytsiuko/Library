package ukma.hrytsiuk.library.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukma.hrytsiuk.library.book.dto.BookCreateDto;
import ukma.hrytsiuk.library.book.dto.BookResponseDto;
import ukma.hrytsiuk.library.book.exception.BookIsbnAlreadyExistsException;
import ukma.hrytsiuk.library.book.exception.BookNotFoundException;
import ukma.hrytsiuk.library.db.entities.book.BookRepository;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public BookResponseDto getById(Integer id) {
        var result = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return BookResponseDto.fromEntity(result);
    }

    @Transactional
    public void addNewBook(BookCreateDto createDto) {
        if (bookRepository.existsByIsbnIgnoreCase(createDto.getIsbn())) {
            throw new BookIsbnAlreadyExistsException(createDto.getIsbn());
        }
        BookEntity bookEntity = createDto.toEntity();
        bookRepository.save(bookEntity);
    }
}
