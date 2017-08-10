package reshef.minihackernews.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reshef.minihackernews.api.model.Post;
import reshef.minihackernews.api.repositories.posts.PostsRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PostsStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(PostsStatisticsService.class);

    @Value("${hot.posts.list.size}")
    private int listSize;

    @Autowired
    private PostsRepository postsRepository;

    private List<Post> topPosts = null;

    @PostConstruct
    private void init() {
        logger.info("loading " + listSize + " top posts from db");
        topPosts = postsRepository.findTopPosts(listSize);
        logger.info("top posts:\n" + topPosts);
    }

    public List<Post> getTopPosts() {
        return topPosts;
    }
}
