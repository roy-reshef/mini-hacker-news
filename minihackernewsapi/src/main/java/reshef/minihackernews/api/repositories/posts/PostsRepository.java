package reshef.minihackernews.api.repositories.posts;

import org.springframework.data.mongodb.repository.MongoRepository;
import reshef.minihackernews.api.model.Post;

public interface PostsRepository extends MongoRepository<Post, String>, PostsRepositoryCustom {
}
