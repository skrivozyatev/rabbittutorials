package ksa.learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author s.krivozyatev 08.07.19 11:54
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    @Profile("usage_message")
    @Bean
    public CommandLineRunner usage() {
        return args -> System.out.println("You should pass profiles with: "
                + "java -jar rabbit-tutorials.jar --spring.profiles.active=tut1,hello-world,sender");
    }

    @Profile("!usage_message")
    @Bean
    public CommandLineRunner tutorial() {
        return new TutorialsRunner();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
