package ksa.learn.tut3;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author s.krivozyatev 08.07.19 16:09
 */
public class Tut3Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanout;

    private AtomicInteger dots = new AtomicInteger(0);
    private AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder sb = new StringBuilder();
        if (dots.incrementAndGet() == 4) {
            dots.set(1);
        }
        for (int i = 0; i < dots.get(); i++) {
            sb.append('.');
        }
        sb.append(count.incrementAndGet());
        String message = sb.toString();
        rabbitTemplate.convertAndSend(fanout.getName(), message);
        System.out.println("[+] Sent " + message);
    }
}
