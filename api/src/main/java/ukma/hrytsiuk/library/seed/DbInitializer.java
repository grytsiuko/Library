package ukma.hrytsiuk.library.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ukma.hrytsiuk.library.book.BookRepository;
import ukma.hrytsiuk.library.book.model.BookEntity;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DbInitializer {

    private final BookRepository bookRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        bookRepository.saveAll(
                List.of(
                        BookEntity.builder()
                                .title("Harry Potter 1")
                                .author("J.K. Rowling")
                                .isbn("1-11-111111-1")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 2")
                                .author("J.K. Rowling")
                                .isbn("2-22-222222-2")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 3")
                                .author("J.K. Rowling")
                                .isbn("3-33-333333-3")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 4")
                                .author("J.K. Rowling")
                                .isbn("4-44-444444-4")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 5")
                                .author("J.K. Rowling")
                                .isbn("5-55-555555-5")
                                .build(),
                        BookEntity.builder()
                                .title("Harry Potter 6")
                                .author("J.K. Rowling")
                                .isbn("6-66-666666-6")
                                .build(),
                        BookEntity.builder()
                                .title("The Adventures of Sherlock Holmes")
                                .author("A.C. Doyle")
                                .isbn("7-77-777777-7")
                                .build(),
                        BookEntity.builder()
                                .title("The Memoirs of Sherlock Holmes")
                                .author("A.C. Doyle")
                                .isbn("8-88-888888-8")
                                .build(),
                        BookEntity.builder()
                                .title("The Return of Sherlock Holmes")
                                .author("A.C. Doyle")
                                .isbn("9-99-999999-9")
                                .build()
                )
        );
    }
}
