package reshef.minihackernews.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {

    @Id
    private String name;
    private Set<String> votedPosts;

    public User() {
    }


    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getVotedPosts() {
        return votedPosts;
    }

    public void setVotedPosts(Set<String> votedPosts) {
        this.votedPosts = votedPosts;
    }

    public boolean hasVotedFor(String postId) {
        return (votedPosts == null) ? false : votedPosts.contains(postId);
    }

    public boolean addVote(String postId) {
        if (votedPosts == null)
            votedPosts = new HashSet<>();

        return votedPosts.add(postId);
    }
}
