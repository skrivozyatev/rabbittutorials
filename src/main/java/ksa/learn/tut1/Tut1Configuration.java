package ksa.learn.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author s.krivozyatev 08.07.19 11:51
 */
@Profile({"tut1", "hello"})
@Configuration
public class Tut1Configuration {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }
}
