package reshef.minihackernews.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reshef.minihackernews.api.services.PostsService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private PostsService service;

    @PostConstruct
    private void init() {
        logger.info("initializing mini hacker news api service");
    }

    @Override
    public void run(String... strings) throws Exception {
        // todo: base on profiles
        service.deleteAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}