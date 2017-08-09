package reshef.minihackernews.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reshef.minihackernews.api.repositories.PostsRepository;

@Service
public class PostsService {

    @Autowired
    private PostsRepository repository;

}
