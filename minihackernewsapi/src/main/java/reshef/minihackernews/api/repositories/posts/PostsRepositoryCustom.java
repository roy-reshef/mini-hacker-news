package reshef.minihackernews.api.repositories.posts;

import reshef.minihackernews.api.model.Post;

import java.util.List;

public interface PostsRepositoryCustom {

    List<Post> findTopPosts(int number);
}
