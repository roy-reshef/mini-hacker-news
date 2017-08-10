package reshef.minihackernews.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reshef.minihackernews.api.model.Post;
import reshef.minihackernews.api.repositories.posts.PostsRepository;
import reshef.minihackernews.api.utils.RankedPostsFixedList;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Profile("production")
public class PostsStatisticsServiceImpl implements PostsStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(PostsStatisticsServiceImpl.class);

    @Value("${hot.posts.list.size}")
    private int listSize;

    @Autowired
    private PostsRepository postsRepository;

    private RankedPostsFixedList topPosts = null;

    @PostConstruct
    private void init() {
        reload();
    }

    public List<Post> getTopPosts() {
        return topPosts;
    }

    public void updateList(Post p) {
        logger.debug("updating top posts list");
        boolean added = topPosts.add(p);
        logger.debug("list updated? - " + added);
    }

    public void reload() {
        logger.info("loading " + listSize + " top posts from db");
        topPosts = new RankedPostsFixedList(listSize, postsRepository.findTopPosts(listSize));
        logger.info("top posts:\n" + topPosts);
    }
}
