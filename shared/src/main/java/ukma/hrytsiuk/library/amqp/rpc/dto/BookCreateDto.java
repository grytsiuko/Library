package ukma.hrytsiuk.library.amqp.rpc.dto;

import lombok.*;
import ukma.hrytsiuk.library.db.entities.book.model.BookEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookCreateDto implements Serializable {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]{4,}$")
    private String title;

    @NotNull
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$")
    private String isbn;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 \\.]{4,}$")
    private String author;

    public BookEntity toEntity() {
        return BookEntity.builder()
                .title(getTitle())
                .isbn(getIsbn())
                .author(getAuthor())
                .build();
    }
}
