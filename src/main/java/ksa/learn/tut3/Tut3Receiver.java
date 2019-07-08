package ksa.learn.tut3;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * @author s.krivozyatev 08.07.19 16:09
 */
public class Tut3Receiver {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String message) throws InterruptedException {
        receive(message, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String message) throws InterruptedException {
        receive(message, 2);
    }

    private void receive(String message, int receiver) throws InterruptedException {
        StopWatch watch = new StopWatch();
        System.out.println("[+] Received  " + message + " instance " + receiver);
        watch.start();
        doWork(message);
        watch.stop();
        System.out.println("[+] Processed " + message + " instance " + receiver + " in " + watch.getTotalTimeMillis());
    }

    private void doWork(String message) throws InterruptedException {
        for (char ch : message.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
