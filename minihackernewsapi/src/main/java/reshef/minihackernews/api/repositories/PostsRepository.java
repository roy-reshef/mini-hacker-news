package reshef.minihackernews.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import reshef.minihackernews.api.model.Post;

public interface PostsRepository extends MongoRepository<Post, String> {
}
