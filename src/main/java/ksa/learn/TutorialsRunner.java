package ksa.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author s.krivozyatev 08.07.19 13:02
 */
public class TutorialsRunner implements CommandLineRunner {

    @Value("${tutorial.client.duration:0}")
    private int duration;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run for duration: " + duration);
        Thread.sleep(duration);
        applicationContext.close();
    }
}
