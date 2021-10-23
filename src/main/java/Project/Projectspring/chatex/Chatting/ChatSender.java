package Project.Projectspring.chatex.Chatting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatSender {

    private final RabbitMessagingTemplate template;

    public void send(String group_code, String message) {
        log.info("chatsender.sender : " + message);
//        template.convertAndSend("chat-exchange", "/room/test", message);
//        template.convertAndSend("/topic/messages", message);
        template.convertAndSend("/topic/messages", message);
        template.convertAndSend("chat-exchange", "room.".concat(String.valueOf(group_code)) ,message);
    }
}
