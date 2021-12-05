package ukma.hrytsiuk.library.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ukma.hrytsiuk.library.domain.book.dto.BookResponseDto;
import ukma.hrytsiuk.library.redis.RedisRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRedisRepository implements RedisRepository<BookResponseDto, Integer> {

    private static final String KEY = "BOOK";

    private final RedisTemplate<String, BookResponseDto> redisTemplate;

    @Override
    public Optional<BookResponseDto> getById(Integer id) {
        var cache = (BookResponseDto) redisTemplate.opsForHash().get(KEY, id);
        return Optional.ofNullable(cache);
    }

    @Override
    public void save(Integer id, BookResponseDto dto) {
        redisTemplate.opsForHash().put(KEY, id, dto);
    }
}
