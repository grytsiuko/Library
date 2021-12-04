package ukma.hrytsiuk.library.book.exception;

import org.apache.http.HttpStatus;
import ukma.hrytsiuk.library.common.MvcException;

public class BookIsbnAlreadyExistsException extends MvcException {
    public BookIsbnAlreadyExistsException(String isbn) {
        super(HttpStatus.SC_CONFLICT, "Book with such isbn already exists: " + isbn);
    }
}
