package reshef.minihackernews.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import reshef.minihackernews.api.model.User;

public interface UsersRepository extends MongoRepository<User, String> {
}
