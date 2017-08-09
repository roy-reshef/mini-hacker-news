package reshef.minihackernews.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reshef.minihackernews.api.model.Post;
import reshef.minihackernews.api.repositories.PostsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PostsService {

    @Autowired
    private PostsRepository repository;

    public List<Post> getAll() {
        return repository.findAll();
    }

    public Post getById(String id) {
        return null;
    }

    public String add(Post p) {
        p.setId(UUID.randomUUID().toString());
        repository.save(p);
        return p.getId();
    }

    public void update(Post p) {

    }

    public void deleteById(String id) {

    }

    public void deleteAll() {

    }
}
