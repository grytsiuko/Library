package ukma.hrytsiuk.library.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponseDto {
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
