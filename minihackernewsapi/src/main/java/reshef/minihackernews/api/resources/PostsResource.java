package reshef.minihackernews.api.resources;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reshef.minihackernews.api.Application;
import reshef.minihackernews.api.dtos.NewPostDto;
import reshef.minihackernews.api.dtos.NewPostResponseDto;
import reshef.minihackernews.api.dtos.PostDto;
import reshef.minihackernews.api.dtos.UpdatePostDto;
import reshef.minihackernews.api.model.Post;
import reshef.minihackernews.api.services.PostsService;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/minihackernews")
public class PostsResource {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private PostsService service;

    private Post createPost(NewPostDto newPostDto) {
        return new Post(newPostDto.getAuthor(),
                newPostDto.getTitle(), newPostDto.getText());
    }

    private PostDto toPostDto(Post post) {
        return new PostDto(post.getId(), post.getAuthor(), post.getTitle(), post.getText());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Post> getAll() {
        logger.info("get all posts");
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Callable<PostDto> get(@PathVariable("id") String id) {
        logger.info("get by id:" + id);

        return () -> {
            Post post = service.getById(id);
            Preconditions.checkNotNull(post);
            return toPostDto(post);
        };
    }

    @RequestMapping(method = RequestMethod.POST)
    public Callable<NewPostResponseDto> add(@RequestBody NewPostDto newPost) throws Exception {
        logger.info("adding post:" + newPost.toString());
        Preconditions.checkNotNull(newPost.getAuthor());
        Preconditions.checkNotNull(newPost.getTitle());
        Preconditions.checkNotNull(newPost.getText());

        return () -> {
            Post post = createPost(newPost);
            String id = service.add(post);

            return new NewPostResponseDto(id);
        };
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Callable<Void> update(@PathVariable("id") String id, @RequestBody UpdatePostDto newPost) throws Exception {
        logger.info("updating post. id=" + id);
        Preconditions.checkNotNull(newPost.getText());

        return () -> {
            Post post = service.getById(id);
            post.setText(newPost.getText());
            service.update(post);
            return null;
        };
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Callable<Void> delete(@PathVariable("id") String id) {
        logger.info("delete post by id:" + id);
        return () -> {
            service.deleteById(id);
            return null;
        };
    }
}
