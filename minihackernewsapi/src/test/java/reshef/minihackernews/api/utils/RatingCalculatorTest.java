package reshef.minihackernews.api.utils;

import org.assertj.core.api.Fail;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reshef.minihackernews.api.model.Post;

public class RatingCalculatorTest {

    private static final Logger logger = LoggerFactory.getLogger(RatingCalculatorTest.class);

    @Test
    public void testPostRatingSameTime() throws Exception {
        Post post1 = new Post("1", "roy", "test title", "test content");
        post1.setUpvotes(30);
        post1.setDownvotes(3);

        Post post2 = new Post("2", "roy", "test title2", "test content2");
        post2.setUpvotes(50);
        post2.setDownvotes(10);

        // set same creation time for testing
        long creationTime = System.currentTimeMillis() - 10000;
        post1.setCreationTime(creationTime);
        post2.setCreationTime(creationTime);

        logger.info("testing rating calculation for two posts with same creation time:\n" +
        post1 + "\n" +
        post2);

        double post1Rating = RatingCalculator.rate(post1);
        logger.info("post1 rating:" + post1Rating);
        double post2Rating = RatingCalculator.rate(post2);
        logger.info("post2 rating:" + post2Rating);

        // post2 should have higher rating
        if (post2Rating < post1Rating)
            Fail.fail("rating calculation with same creation time failed");
    }

    @Test
    public void testPostRatingSameVotesDiff() throws Exception {
        Post post1 = new Post("1", "roy", "test title", "test content");
        post1.setUpvotes(30);
        post1.setDownvotes(3);

        Post post2 = new Post("2", "roy", "test title2", "test content2");
        post2.setUpvotes(30);
        post2.setDownvotes(3);

        // set same creation time for testing
        post1.setCreationTime(System.currentTimeMillis() - 10000);
        post2.setCreationTime(System.currentTimeMillis() - 20000);


        logger.info("testing rating calculation for two posts with same votes diff:\n" +
                post1 + "\n" +
                post2);

        double post1Rating = RatingCalculator.rate(post1);
        logger.info("post1 rating:" + post1Rating);
        double post2Rating = RatingCalculator.rate(post2);
        logger.info("post2 rating:" + post2Rating);

        // post1 should have higher rating
        if (post1Rating < post2Rating)
            Fail.fail("rating calculation with same votes diff failed");
    }
}