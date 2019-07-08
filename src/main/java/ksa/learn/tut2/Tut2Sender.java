package ksa.learn.tut2;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author s.krivozyatev 08.07.19 14:36
 */
public class Tut2Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue workersQueue;

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
        rabbitTemplate.convertAndSend(workersQueue.getName(), message);
        System.out.println("[+] Sent " + message);
    }
}
