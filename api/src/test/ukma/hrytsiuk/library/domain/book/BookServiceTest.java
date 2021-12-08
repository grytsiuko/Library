package ukma.hrytsiuk.library.domain.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ukma.hrytsiuk.library.amqp.dto.AmqpResponse;
import ukma.hrytsiuk.library.amqp.rpc.RPCBookServiceInterface;
import ukma.hrytsiuk.library.amqp.rpc.dto.BookCreateDto;
import ukma.hrytsiuk.library.db.entities.book.BookRepository;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;
import ukma.hrytsiuk.library.domain.book.dto.BookResponseDto;
import ukma.hrytsiuk.library.domain.book.exception.BookNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookRedisRepository bookRedisRepository;
    @Mock
    private RPCBookServiceInterface rpcBookServiceInterface;

    @InjectMocks
    private BookService bookService;

    private static final int EXAMPLE_ID = 1;
    private static final String EXAMPLE_TITLE = "Title";
    private static final String EXAMPLE_AUTHOR = "Author";
    private static final String EXAMPLE_ISBN = "0123456789";

    private static BookResponseDto getExampleResponseDto() {
        return new BookResponseDto(EXAMPLE_ID, EXAMPLE_TITLE, EXAMPLE_AUTHOR, EXAMPLE_ISBN);
    }

    private static BookEntity getExampleEntity() {
        return new BookEntity(EXAMPLE_ID, EXAMPLE_TITLE, EXAMPLE_ISBN, EXAMPLE_AUTHOR);
    }

    private static BookCreateDto getExampleBookCreateDto() {
        return new BookCreateDto(EXAMPLE_TITLE, EXAMPLE_ISBN, EXAMPLE_AUTHOR);
    }

    @Test
    void getById_whenPresentInCache_shouldSucceed() {
        var cacheResult = Optional.of(getExampleResponseDto());
        when(bookRedisRepository.getById(EXAMPLE_ID)).thenReturn(cacheResult);

        var response = bookService.getById(EXAMPLE_ID);

        assertEquals(EXAMPLE_ID, response.getId());
        assertEquals(EXAMPLE_TITLE, response.getTitle());
        assertEquals(EXAMPLE_AUTHOR, response.getAuthor());
        assertEquals(EXAMPLE_ISBN, response.getIsbn());

        verify(bookRepository, never()).findById(any());
        verify(bookRedisRepository, never()).save(any(), any());
    }

    @Test
    void getById_whenAbsentInCache_shouldSucceed() {
        var dbResult = Optional.of(getExampleEntity());
        when(bookRedisRepository.getById(EXAMPLE_ID)).thenReturn(Optional.empty());
        when(bookRepository.findById(EXAMPLE_ID)).thenReturn(dbResult);

        var response = bookService.getById(EXAMPLE_ID);

        assertEquals(EXAMPLE_ID, response.getId());
        assertEquals(EXAMPLE_TITLE, response.getTitle());
        assertEquals(EXAMPLE_AUTHOR, response.getAuthor());
        assertEquals(EXAMPLE_ISBN, response.getIsbn());

        verify(bookRedisRepository, times(1)).save(eq(EXAMPLE_ID), eq(response));
    }

    @Test
    void getById_whenNotExists_shouldThrowError() {
        when(bookRedisRepository.getById(EXAMPLE_ID)).thenReturn(Optional.empty());
        when(bookRepository.findById(EXAMPLE_ID)).thenReturn(Optional.empty());

        assertThrows(
                BookNotFoundException.class,
                () -> bookService.getById(EXAMPLE_ID)
        );

        verify(bookRedisRepository, never()).save(any(), any());
    }




    @Test
    void addNewBook_whenSuccess_shouldSucceed() {
        var request = getExampleBookCreateDto();
        when(rpcBookServiceInterface.save(request)).thenReturn(AmqpResponse.success(EXAMPLE_ID));

        var response = bookService.addNewBook(request);

        assertEquals(EXAMPLE_ID, response);
    }

    @Test
    void addNewBook_whenError_shouldThrowError() {
        var request = getExampleBookCreateDto();
        var exception = new RuntimeException();
        when(rpcBookServiceInterface.save(request)).thenReturn(AmqpResponse.error(exception));

        assertThrows(
                RuntimeException.class,
                () -> bookService.addNewBook(request)
        );
    }

}