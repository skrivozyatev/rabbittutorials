package ksa.learn.tut2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * @author s.krivozyatev 08.07.19 14:36
 */
@RabbitListener(queues = "workers")
public class Tut2Receiver {

    private final int instance;

    public Tut2Receiver(int instance) {

        this.instance = instance;
    }

    @RabbitHandler
    public void receive(String message) throws InterruptedException {
        StopWatch watch = new StopWatch();
        System.out.println("[+] Received  " + message + " instance " + instance);
        watch.start();
        doWork(message);
        watch.stop();
        System.out.println("[+] Processed " + message + " instance " + instance + " in " + watch.getTotalTimeMillis());
    }

    private void doWork(String message) throws InterruptedException {
        for (char ch : message.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
