package reshef.minihackernews.api.repositories.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reshef.minihackernews.api.model.Post;

import java.util.List;

public class PostsRepositoryImpl implements PostsRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Post> findTopPosts(int number) {
        final Pageable pageableRequest = new PageRequest(0, number);

        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "rating")).with(pageableRequest);
        List<Post> posts = mongoTemplate.find(query, Post.class);

        return posts;
    }
}
