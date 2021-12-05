package ukma.hrytsiuk.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import ukma.hrytsiuk.library.domain.book.dto.BookResponseDto;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, BookResponseDto> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, BookResponseDto> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
