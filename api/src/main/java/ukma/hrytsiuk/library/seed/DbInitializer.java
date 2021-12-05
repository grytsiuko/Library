package ukma.hrytsiuk.library.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ukma.hrytsiuk.library.db.entities.book.BookRepository;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DbInitializer {

    private final BookRepository bookRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var count = bookRepository.count();
        if (count > 0) {
            return;
        }
        bookRepository.saveAll(
                List.of(
                        BookEntity.builder()
                                .title("Harry Potter 1")
                                .author("J.K. Rowling")
                                .isbn("1111111111")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 2")
                                .author("J.K. Rowling")
                                .isbn("2222222222")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 3")
                                .author("J.K. Rowling")
                                .isbn("3333333333")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 4")
                                .author("J.K. Rowling")
                                .isbn("4444444444")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 5")
                                .author("J.K. Rowling")
                                .isbn("5555555555")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 6")
                                .author("J.K. Rowling")
                                .isbn("6666666666")
                                .build(),
                        BookEntity.builder()
                                .title("The Adventures of Sherlock Holmes")
                                .author("A.C. Doyle")
                                .isbn("7777777777")
                                .build(),
                        BookEntity.builder()
                                .title("The Memoirs of Sherlock Holmes")
                                .author("A.C. Doyle")
                                .isbn("8888888888")
                                .build(),
                        BookEntity.builder()
                                .title("The Return of Sherlock Holmes")
                                .author("A.C. Doyle")
                                .isbn("9999999999")
                                .build()
                )
        );
    }
}
