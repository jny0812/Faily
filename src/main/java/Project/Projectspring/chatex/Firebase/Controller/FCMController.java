package Project.Projectspring.chatex.Firebase.Controller;

import Project.Projectspring.chatex.Firebase.Service.FirebaseInit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FCMController {

    private final FirebaseInit init;
    @GetMapping("/v1")
    public String v1(){
        init.init();
        return "firebase_fcm";
    }}
