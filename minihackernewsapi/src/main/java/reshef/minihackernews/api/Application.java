package reshef.minihackernews.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @PostConstruct
    private void init() {
        logger.info("initializing mini hacker news api service");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}