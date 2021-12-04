package ukma.hrytsiuk.library.domain.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponseDto implements Serializable {
    private Integer id;
    private String title;
    private String author;
    private String isbn;

    public static BookResponseDto fromEntity(BookEntity bookEntity) {
        return new BookResponseDto(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getIsbn()
        );
    }
}
