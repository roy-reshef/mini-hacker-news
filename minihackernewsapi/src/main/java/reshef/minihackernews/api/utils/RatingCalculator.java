package reshef.minihackernews.api.utils;

import reshef.minihackernews.api.model.Post;

public interface RatingCalculator {
    static double rate(Post post) {
        double voteDiff = post.getUpvotes() - post.getDownvotes();
        double timeDiff = System.currentTimeMillis() - post.getCreationTime();
        return voteDiff / timeDiff;
    }
}
