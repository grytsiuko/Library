package ukma.hrytsiuk.library.domain.book.exception;

import org.apache.http.HttpStatus;
import ukma.hrytsiuk.library.errors.MvcException;

public class BookIsbnAlreadyExistsException extends MvcException {
    public BookIsbnAlreadyExistsException(String isbn) {
        super(HttpStatus.SC_CONFLICT, "Book with such isbn already exists: " + isbn);
    }
}
