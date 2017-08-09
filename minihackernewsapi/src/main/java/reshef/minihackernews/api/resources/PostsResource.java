package reshef.minihackernews.api.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reshef.minihackernews.api.Application;
import reshef.minihackernews.api.dtos.NewPostDto;
import reshef.minihackernews.api.model.Post;

import java.util.List;

@RestController
@RequestMapping("/minihackernews")
public class PostsResource {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Post> getAll() {
        logger.info("get all posts");
        return null;
    }

    /**
     * path: /minihackernews/{id}
     *
     * @param id
     * @return List<ServiceDTO>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post get(@PathVariable("id") String id) {
        logger.info("get by id:" + id);
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody NewPostDto newPost) throws Exception {
        logger.info("adding post:" + newPost.toString());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        logger.info("delete post by id:" + id);
    }
}
