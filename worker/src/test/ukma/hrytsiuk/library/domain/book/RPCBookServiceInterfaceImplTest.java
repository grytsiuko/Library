package ukma.hrytsiuk.library.domain.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ukma.hrytsiuk.library.amqp.rpc.dto.BookCreateDto;
import ukma.hrytsiuk.library.amqp.rpc.exceptions.BookIsbnAlreadyExistsException;
import ukma.hrytsiuk.library.db.entities.book.BookRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RPCBookServiceInterfaceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private RPCBookServiceInterfaceImpl rpcBookServiceInterface;

    private static final String EXAMPLE_TITLE = "Title";
    private static final String EXAMPLE_AUTHOR = "Author";
    private static final String EXAMPLE_ISBN = "0123456789";

    private static BookCreateDto getExampleBookCreateDto() {
        return new BookCreateDto(EXAMPLE_TITLE, EXAMPLE_ISBN, EXAMPLE_AUTHOR);
    }

    @Test
    void save_whenUniqueIsbn_shouldSucceed() {
        var request = getExampleBookCreateDto();
        when(bookRepository.existsByIsbnIgnoreCase(EXAMPLE_ISBN)).thenReturn(false);

        var response = rpcBookServiceInterface.save(request);

        assertTrue(response.isSuccess());
    }

    @Test
    void save_whenDuplicatedIsbn_shouldReturnError() {
        var request = getExampleBookCreateDto();
        when(bookRepository.existsByIsbnIgnoreCase(EXAMPLE_ISBN)).thenReturn(true);

        var response = rpcBookServiceInterface.save(request);

        assertFalse(response.isSuccess());
        assertInstanceOf(BookIsbnAlreadyExistsException.class, response.getException());
    }

}