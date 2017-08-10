package reshef.minihackernews.api.utils;

import reshef.minihackernews.api.model.Post;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class RankedPostsFixedList extends ArrayList<Post> {

    private int sizeLimit;

    public RankedPostsFixedList(int sizeLimit) {
        this(sizeLimit, null);
    }

    public RankedPostsFixedList(int sizeLimit, List<Post> posts) {
        super();
        this.sizeLimit = sizeLimit;
        if (posts != null)
            addAllInternal(posts);
    }

    private void addAllInternal(Collection<? extends Post> collection) {
        collection.stream().forEach(p -> add(p));
    }

    private int isFound(Post p) {
        for(int i=0;i<size();i++) {
            if(get(i).getId().equals(p.getId()))
                return i;
        }
        return -1;
    }

    @Override
    public boolean add(Post post) {
        int idx = isFound(post);
        if(idx > -1) {
            // already found in list. replace
            set(idx, post);
            sort(new PostComparator());
            return true;
        }

        if (size() < sizeLimit) {
            super.add(post);
            sort(new PostComparator());
            return true;
        } else {
            int pos = 0;

            // find position in list to add new post
            while (pos < sizeLimit && get(pos).getRating() > post.getRating()) {
                pos++;
            }

            if (pos < sizeLimit) {
                // if was found then move everything down 1 position
                for (int i = pos; i < size()-1; i++)
                    set(i + 1, get(i));

                // insert new post to position
                set(pos, post);
                sort(new PostComparator());
                return true;
            }
        }
        return false;
    }

    private class PostComparator implements Comparator<Post> {

        public int compare(Post post1, Post post2) {

            double ranking1 = post1.getRating();
            double ranking2 = post2.getRating();

            return (ranking1 > ranking2) ? -1 : 1;
        }
    }
}
