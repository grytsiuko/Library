package ukma.hrytsiuk.library.amqp.rpc.exceptions;

import org.apache.http.HttpStatus;
import ukma.hrytsiuk.library.errors.MvcException;

public class BookIsbnAlreadyExistsException extends MvcException {
    public BookIsbnAlreadyExistsException(String isbn) {
        super(HttpStatus.SC_CONFLICT, "Book with such isbn already exists: " + isbn);
    }
}
