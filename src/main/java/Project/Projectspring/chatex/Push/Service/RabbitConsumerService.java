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

            int receiver_id = Integer.parseInt(String.valueOf(receivers.get(i).getUser_id()));
            log.warn(String.valueOf(receiver_id));
            String content = chatVO.getContent();

            String sender_name = chatVO.getSender_name();

            int sender_id = pushService.findIdbyName(sender_name);

            chatVO.setSender_id(sender_id);

            Date time = new Date();
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Calendar cal = Calendar.getInstance();
            cal.setTime(time);
            String chatting_time = sdformat.format(cal.getTime());  //채팅 시간

            chatVO.setChatting_time(chatting_time);
            log.warn(String.valueOf(chatting_time));
            chatVO.setReceiver_id(receiver_id);

            pushService.putChatting(chatVO);
        }

    }


}
