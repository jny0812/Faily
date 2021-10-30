package Project.Projectspring.chatex.Push.Service;

import Project.Projectspring.chatex.Push.Service.PushService;
import Project.Projectspring.chatex.Push.VO.ChatVO;
import Project.Projectspring.chatex.Push.VO.ReceiverListVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitConsumerService {

    private final PushService pushService;

    @RabbitListener(queues = "chat", concurrency = "6")
    public void pushChatConsumer(ChatVO chatVO) throws Exception {

        String group_code = chatVO.getGroup_code();
        log.warn(group_code);
        List<ReceiverListVO> receivers =  pushService.Receivers(group_code); //receiver 리스트

        for(int i=0;i<receivers.size();i++) {
            pushService.sendByToken(chatVO); //알림

        }

    }


}
