package reshef.minihackernews.api.dtos;

public class PostDto {

    private String id;
    private String author;
    private String title;
    private String text;
    private int upvotes;
    private int downvotes;
    private long creationTime;
    double rating;

    public PostDto() {
    }

    public PostDto(String id, String author, String title, String text,
                   int upvotes, int downvotes, long creationTime, double rating) {
        this.author = author;
        this.title = title;
        this.id = id;
        this.text = text;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.creationTime = creationTime;
        this.rating = rating;
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

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", upvotes=").append(upvotes);
        sb.append(", downvotes=").append(downvotes);
        sb.append('}');
        return sb.toString();
    }
}
