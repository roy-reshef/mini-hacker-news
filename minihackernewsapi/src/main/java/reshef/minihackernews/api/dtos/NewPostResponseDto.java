package reshef.minihackernews.api.dtos;

public class NewPostResponseDto {

    private String id;

    public NewPostResponseDto() {
    }

    public NewPostResponseDto(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
