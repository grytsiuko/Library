package ukma.hrytsiuk.library.domain.book.exception;

import org.apache.http.HttpStatus;
import ukma.hrytsiuk.library.common.MvcException;

public class BookNotFoundException extends MvcException {
    public BookNotFoundException() {
        super(HttpStatus.SC_NOT_FOUND, "Book not found");
    }
}
