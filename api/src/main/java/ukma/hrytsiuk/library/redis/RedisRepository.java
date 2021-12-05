package ukma.hrytsiuk.library.redis;

import java.util.Optional;

public interface RedisRepository<Entity, Id> {

    Optional<Entity> getById(Id id);

    void save(Id id, Entity entity);
}
