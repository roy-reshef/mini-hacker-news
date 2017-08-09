package reshef.minihackernews.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reshef.minihackernews.api.model.Post;
import reshef.minihackernews.api.repositories.PostsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PostsService {

    private static final Logger logger = LoggerFactory.getLogger(PostsService.class);

    @Autowired
    private PostsRepository repository;

    public List<Post> getAll() {
        return repository.findAll();
    }

    public Post getById(String id) {
        return repository.findOne(id);
    }

    public String add(Post post) {
        post.setId(UUID.randomUUID().toString());
        logger.info("saving new post:" + post);
        repository.save(post);
        return post.getId();
    }

    public void update(Post post) {
        logger.info("updating post:" + post);
        repository.save(post);
    }

    public void deleteById(String id) {
        repository.delete(id);
    }

    public void deleteAll() {

    }
}
