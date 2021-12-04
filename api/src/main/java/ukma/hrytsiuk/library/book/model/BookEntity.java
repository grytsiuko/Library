package ukma.hrytsiuk.library.book.model;

import lombok.*;
import ukma.hrytsiuk.library.book.dto.BookCreateDto;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class BookEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "author", nullable = false)
    private String author;

    public static BookEntity fromDto(BookCreateDto createDto) {
        return BookEntity.builder()
                .title(createDto.getTitle())
                .isbn(createDto.getIsbn())
                .author(createDto.getAuthor())
                .build();
    }
}
