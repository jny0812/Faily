package Project.Projectspring.chatex.Chatting.Controller;

import Project.Projectspring.chatex.Chatting.VO.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public MessageVO sendMessage(final MessageVO messageVO) throws InterruptedException {
        Thread.sleep(1000);
        log.info("================= input : " + messageVO.getContent());
        return new MessageVO(messageVO.getContent());
    }
}
