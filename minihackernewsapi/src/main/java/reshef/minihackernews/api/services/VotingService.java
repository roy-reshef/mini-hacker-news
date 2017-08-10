package reshef.minihackernews.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reshef.minihackernews.api.dtos.PostVoteDto;
import reshef.minihackernews.api.model.Post;
import reshef.minihackernews.api.model.User;
import reshef.minihackernews.api.repositories.UsersRepository;
import reshef.minihackernews.api.utils.RatingCalculator;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class VotingService {

    private static final Logger logger = LoggerFactory.getLogger(VotingService.class);

    @Autowired
    private PostsService postsService;

    @Autowired
    private UsersRepository usersRepository;

    private ExecutorService executorService;

    @PostConstruct
    private void init() {
        logger.info("initializing voting service");
        executorService = Executors.newFixedThreadPool(1);
    }

    public void updateVotes(String postId, PostVoteDto voteDto) {
        executorService.submit(new UpdaterTask(postId, voteDto));
    }

    private class UpdaterTask implements Runnable {

        private String postId;
        private PostVoteDto postVoteDto;

        public UpdaterTask(String postId, PostVoteDto postVoteDto) {
            this.postId = postId;
            this.postVoteDto = postVoteDto;
        }

        @Override
        public void run() {
            Post post = postsService.getById(postId);

            if (post != null) {
                boolean shouldUpdate = true;

                User user = usersRepository.findOne(postVoteDto.getVoter());
                // normally a user will be created on registration
                if (user == null) {
                    logger.info("user does not exist. creating");
                    user = new User(postVoteDto.getVoter());
                    user.addVote(postId);
                } else {
                    if (user.hasVotedFor(postId)) {
                        shouldUpdate = false;
                    } else {
                        user.addVote(postId);
                    }
                }

                if (shouldUpdate) {
                    if (postVoteDto.isUp()) {
                        logger.info("updating up votes for post:" + postId);
                        post.upVote();
                    } else {
                        logger.info("updating down votes for post:" + postId);
                        post.downVote();
                    }

                    post.setRating(RatingCalculator.rate(post));

                    postsService.update(post);
                    usersRepository.save(user);
                } else {
                    logger.info("User has already voted for this post");
                }
            } else {
                logger.error("post with id '" + postId + "' was not found");
            }
        }
    }
}
