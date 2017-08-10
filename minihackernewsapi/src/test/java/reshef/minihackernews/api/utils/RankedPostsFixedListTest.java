package reshef.minihackernews.api.utils;

import org.junit.Assert;
import org.junit.Test;
import reshef.minihackernews.api.model.Post;

import java.util.ArrayList;
import java.util.List;

public class RankedPostsFixedListTest {

    @Test
    public void testConstruction() {
        Post p1 = new Post("1", "roy", "post1", "post1 content");
        p1.setRating(0.0345);
        Post p2 = new Post("2", "roy", "post1", "post1 content");
        p2.setRating(0.0567);
        List<Post> posts = new ArrayList<Post>() {{
            add(p1);
            add(p2);
        }};
        RankedPostsFixedList rankedList = new RankedPostsFixedList(2, posts);

        Assert.assertEquals(0.0345, rankedList.get(1).getRating(), 0);
    }

    private RankedPostsFixedList getTestList() {
        RankedPostsFixedList list = new RankedPostsFixedList(2);

        Post p1 = new Post("1", "roy", "post1", "post1 content");
        p1.setRating(0.0345);
        Post p2 = new Post("2", "roy", "post1", "post1 content");
        p2.setRating(0.0567);

        list.add(p1);
        list.add(p2);

        return list;
    }

    @Test
    public void testSorting() {
        Assert.assertEquals(0.0567, getTestList().get(0).getRating(), 0);
    }

    @Test
    public void testReplace() {
        RankedPostsFixedList list = getTestList();

        Post p3 = new Post("3", "roy", "post3", "post1 content");
        p3.setRating(0.0456);

        list.add(p3);

        Assert.assertEquals(0.0456, list.get(1).getRating(), 0);

        Post p4 = new Post("4", "roy", "post4", "post1 content");
        p4.setRating(0.0888);

        list.add(p4);

        Assert.assertEquals(0.0567, list.get(1).getRating(), 0);
    }

    @Test
    public void testNegative() {
        RankedPostsFixedList list = getTestList();

        Post p1 = new Post("3", "roy", "post1", "post1 content");
        p1.setRating(0.0111);

        list.add(p1);

        Assert.assertEquals(2, list.size());
    }

    @Test
    public void testSecondVote() {
        RankedPostsFixedList list = new RankedPostsFixedList(2);

        Post p1 = new Post("1", "roy", "post1", "post1 content");
        p1.setRating(0.0345);

        list.add(p1);

        // same id
        Post p2 = new Post("1", "roy", "post1", "post1 content");
        p2.setRating(0.0111);
        list.add(p2);

        Assert.assertEquals(1, list.size());
        Assert.assertEquals(0.0111, list.get(0).getRating(), 0);
    }
}