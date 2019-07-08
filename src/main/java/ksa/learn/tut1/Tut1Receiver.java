package ksa.learn.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author s.krivozyatev 08.07.19 13:31
 */
@RabbitListener(queues = "hello")
public class Tut1Receiver {

    @RabbitHandler
    public void receive(String message) {
        System.out.println("[+] Received " + message);
    }
}
