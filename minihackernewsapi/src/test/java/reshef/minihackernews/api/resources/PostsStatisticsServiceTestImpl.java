package reshef.minihackernews.api.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reshef.minihackernews.api.model.Post;
import reshef.minihackernews.api.services.PostsStatisticsService;
import reshef.minihackernews.api.utils.RankedPostsFixedList;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Profile("test")
public class PostsStatisticsServiceTestImpl implements PostsStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(PostsStatisticsServiceTestImpl.class);

    @Value("${hot.posts.list.size}")
    private int listSize;

    private RankedPostsFixedList topPosts = null;

    @PostConstruct
    private void init() {
        topPosts = new RankedPostsFixedList(listSize);
    }

    public List<Post> getTopPosts() {
        return topPosts;
    }

    public void updateList(Post p) {
        logger.debug("updating top posts list");
        boolean added = topPosts.add(p);
        logger.debug("list updated? - " + added);
    }

    @Override
    public void reload() {

    }
}
