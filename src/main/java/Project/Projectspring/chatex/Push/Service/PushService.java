package Project.Projectspring.chatex.Push.Service;

import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.chatex.Push.DAO.PushDAO;
import Project.Projectspring.chatex.Push.VO.ChatPutVO;
import Project.Projectspring.chatex.Push.VO.ChatVO;
import Project.Projectspring.chatex.Push.VO.ReceiverListVO;
import Project.Projectspring.chatex.Redis.Service.RedisService;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PushService implements PushServicein {
    private RedisService redisService;

    private final PushDAO pushDAO;

//    @Override
//    public String getFcmToken(int user_id) throws Exception {
//
//        return redisService.getRedisStringValue(user_id);
////        return pushDAO.getFcmToken(user_name);  //user_id
//    }

        @Override
    public String getFcmToken(int user_id) throws Exception {

        return redisService.getRedisStringValue(user_id);
//        return pushDAO.getFcmToken(user_name);  //user_id
    }

    @Override
    public List<ReceiverListVO> Receivers(String group_code) throws Exception {
        return pushDAO.Receivers(group_code);
    }

    @Override
    public void putChatting(ChatPutVO chatPutVO) throws Exception {
        pushDAO.putChatting(chatPutVO);

    }


    public void sendByToken(ChatVO chatVO) throws Exception {
//        String fcmToken = getFcmToken(chatVO.getReceiver_id());
        String fcmToken = "fISq3rPN8t8:APA91bFutOCIl-F50-HK0WtHhL7-iS8_wdlxxmfEHpfLwx41u_hKcXW6OY5s9pRy86y61XE7N1jkhp03Y2RK_UqLCUgQIAZkTXFwAzCC1sVZpZeaqO0BcsyTb1MOveDxG1VpbaKRL90Q";

        if (fcmToken != null) {
            if(chatVO.getContent() != null) {
                WebpushNotification webpushNotification = WebpushNotification.builder()
                        .setTitle(chatVO.getSender_name() + "님으로부터 메시지가 도착했습니다.")
                        .setBody(chatVO.getContent())
//                    .setTag(chatVO.getRoomId())
                        .build();
                log.warn("build complete");

                WebpushConfig webpushConfig = WebpushConfig.builder()
                        .setNotification(webpushNotification)
                        .build();

                Message message = Message.builder()
                        .setToken(fcmToken)
                        .setWebpushConfig(webpushConfig)
                        .putData("sender", chatVO.getSender_name())
                        .putData("receiver", String.valueOf(chatVO.getReceiver_id()))
                        .putData("group_code", chatVO.getGroup_code())
                        .build();

                FirebaseMessaging.getInstance().sendAsync(message);
            }else{
                WebpushNotification webpushNotification = WebpushNotification.builder()
                        .setTitle(chatVO.getSender_name() + "님으로부터 메시지가 도착했습니다.")
                        .setBody(chatVO.getFile().toString())
//                    .setTag(chatVO.getRoomId())
                        .build();

                WebpushConfig webpushConfig = WebpushConfig.builder()
                        .setNotification(webpushNotification)
                        .build();

                Message message = Message.builder()
                        .setToken(fcmToken)
                        .setWebpushConfig(webpushConfig)
                        .putData("sender", chatVO.getSender_name())
                        .putData("receiver", String.valueOf(chatVO.getReceiver_id()))
                        .putData("group_code", chatVO.getGroup_code())
                        .build();

                FirebaseMessaging.getInstance().sendAsync(message);
            }
        }
    }

}
