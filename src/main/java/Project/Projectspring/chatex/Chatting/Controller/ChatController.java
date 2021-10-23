package Project.Projectspring.chatex.Chatting.Controller;

import Project.Projectspring.chatex.Chatting.ChatSender;
import Project.Projectspring.chatex.Chatting.VO.MessageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatSender sender;
//    private final MessageController controller;

    @PostMapping("/chat")
    public boolean chatController(final @RequestBody MessageVO messageVO) throws InterruptedException {
//        controller.sendMessage(messageVO);
        sender.send(messageVO.getGroup_code(),messageVO.getContent());
        return true;
    }
}