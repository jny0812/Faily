package Project.Projectspring.chatex.Firebase.Controller;

import Project.Projectspring.chatex.Firebase.Service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FirebaseController {

    private final FirebaseMessagingService FirebaseService;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Note {
        private String subject;
        private String content;
        private Map<String, String> data;
        private String image;
    }


    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note,
                                   @RequestParam String topic) throws FirebaseMessagingException {
        return FirebaseService.sendNotification(note, topic);
    }
}
