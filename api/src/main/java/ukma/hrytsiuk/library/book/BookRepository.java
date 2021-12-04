package ukma.hrytsiuk.library.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ukma.hrytsiuk.library.book.model.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Boolean existsByIsbnIgnoreCase(String isbn);
}
