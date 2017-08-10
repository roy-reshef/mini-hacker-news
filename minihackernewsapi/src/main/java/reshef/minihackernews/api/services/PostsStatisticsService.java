package reshef.minihackernews.api.services;

import reshef.minihackernews.api.model.Post;

import java.util.List;

public interface PostsStatisticsService {

    List<Post> getTopPosts();

    void updateList(Post p);

    void reload();
}
