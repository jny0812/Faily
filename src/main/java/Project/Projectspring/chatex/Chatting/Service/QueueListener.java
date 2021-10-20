package Project.Projectspring.chatex.Chatting.Service;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class QueueListener {

    @RabbitListener(queues = "chat")
    public void Listener(String content, Queue queue) {
        System.out.println("content = " + content);
        System.out.println("queue = " + queue);
    }
}
