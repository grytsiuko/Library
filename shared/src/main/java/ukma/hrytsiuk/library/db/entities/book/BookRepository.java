package ukma.hrytsiuk.library.db.entities.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Boolean existsByIsbnIgnoreCase(String isbn);
}
