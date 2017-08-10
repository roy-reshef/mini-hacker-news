package reshef.minihackernews.api.dtos;

public class PostVoteDto {

    private String voter;
    private boolean up;

    public PostVoteDto() {
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }
}
