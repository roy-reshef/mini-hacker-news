package reshef.minihackernews.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String author;
    private String title;
    private String text;
    private int upvotes = 0;
    private int downvotes = 0;

    public Post() {
    }

    public Post(String author, String title, String text) {
        this(null, author, title, text);
    }

    public Post(String id, String author, String title, String text) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public synchronized void upVote() {
        upvotes++;
    }

    public synchronized void downVote() {
        downvotes++;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("id='").append(id).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", upvotes=").append(upvotes);
        sb.append(", downvotes=").append(downvotes);
        sb.append('}');
        return sb.toString();
    }
}
