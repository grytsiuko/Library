package ukma.hrytsiuk.library.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ukma.hrytsiuk.library.domain.book.dto.BookCreateDto;
import ukma.hrytsiuk.library.domain.book.dto.BookResponseDto;

import javax.validation.Valid;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookService bookService;

    @GetMapping("{id}")
    public BookResponseDto getById(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Integer create(@Valid @RequestBody BookCreateDto createDto) {
        return bookService.addNewBook(createDto);
    }
}
