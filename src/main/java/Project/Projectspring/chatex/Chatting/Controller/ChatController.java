package Project.Projectspring.chatex.Chatting.Controller;

import Project.Projectspring.chatex.Chatting.ChatSender;
import Project.Projectspring.chatex.Chatting.VO.MessageVO;
import Project.Projectspring.chatex.Push.Service.RabbitService;
import Project.Projectspring.chatex.Push.VO.ChatVO;
import lombok.*;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatController {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatRequest {
        private String content;
        private String user_name;
    }

    private final ChatSender sender;
//    private final MessageController controller;
    private final SimpMessageSendingOperations msgTemplate;
    private final RabbitService rabbitService;

    @PostMapping("/chat")
    public boolean chatController(final @RequestBody MessageVO messageVO) throws InterruptedException {
//        controller.sendMessage(messageVO);
        sender.send(messageVO.getGroup_code(),messageVO.getContent());
        return true;
    }

    @RequestMapping(value = "/chat/msg", method = RequestMethod.POST)
    @ResponseBody
    public void Send(@RequestBody ChatVO chatVO) throws Exception {
        msgTemplate.convertAndSend("/topic/messages." + String.valueOf(chatVO.getGroup_code()) , new ChatRequest(chatVO.getContent(), chatVO.getSender_name()));
        rabbitService.rabbitChatProducer(chatVO);
    }
}