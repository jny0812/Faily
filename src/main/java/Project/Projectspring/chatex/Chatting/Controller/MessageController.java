package Project.Projectspring.chatex.Chatting.Controller;

import Project.Projectspring.chatex.Chatting.VO.MessageVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLDecoder;

@Slf4j
@Controller
public class MessageController {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class message_test {
        String content;
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public message_test sendMessage(final message_test messageVO) throws InterruptedException {
        Thread.sleep(1000);
        log.info("================= input : " + URLDecoder.decode(messageVO.getContent()));
//        return new MessageVO("", messageVO.getContent());
        return new message_test(messageVO.getContent());
    }

    @GetMapping("/homeTest")
    public String test() {
        return "room";
    }
}
