package ksa.learn.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author s.krivozyatev 08.07.19 13:29
 */
public class Tut1Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    private int cnt = 0;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String message = "message " + cnt++;
        rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println("[+] Sent " + message);
    }
}
