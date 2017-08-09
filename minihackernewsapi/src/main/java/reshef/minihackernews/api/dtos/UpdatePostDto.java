package reshef.minihackernews.api.dtos;

public class UpdatePostDto {

    private String text;

    public UpdatePostDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
