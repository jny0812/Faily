package Project.Projectspring.chatex.Push.Service;

import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.chatex.Push.DAO.PushDAO;
import Project.Projectspring.chatex.Push.VO.ChatVO;
import Project.Projectspring.chatex.Push.VO.ReceiverListVO;
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

    private final PushDAO pushDAO;

    @Override
    public int getFcmToken(String user_name) throws Exception {
        return pushDAO.getFcmToken(user_name);  //user_id
    }

    @Override
    public List<ReceiverListVO> Receivers(String group_code) throws Exception {
        return pushDAO.Receivers(group_code);
    }

    @Override
    public void putChatting(ChatVO chatVO) throws Exception {
        pushDAO.putChatting(chatVO);
    }

    @Override
    public int findIdbyName(String sender_name) throws Exception {
        return pushDAO.findIdbyName(sender_name);
    }

    public void sendByToken(ChatVO chatVO) {
//        String fcmToken = getFcmToken(chatVO.getReceiver());
        String fcmToken = "fISq3rPN8t8:APA91bFutOCIl-F50-HK0WtHhL7-iS8_wdlxxmfEHpfLwx41u_hKcXW6OY5s9pRy86y61XE7N1jkhp03Y2RK_UqLCUgQIAZkTXFwAzCC1sVZpZeaqO0BcsyTb1MOveDxG1VpbaKRL90Q";

        if (fcmToken != null) {
            WebpushNotification webpushNotification = WebpushNotification.builder()
                    .setTitle(chatVO.getSender_name() + "님으로부터 메시지가 도착했습니다.")
                    .setBody(chatVO.getContent())
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

//    @Transactional
//    public void setTopic(JoinVO joinVO, String topic) throws FirebaseMessagingException {
//
//        String encodedTopic = URLEncoder.encode(topic, StandardCharsets.UTF_8);
//        String fcmToken = "fISq3rPN8t8:APA91bFutOCIl-F50-HK0WtHhL7-iS8_wdlxxmfEHpfLwx41u_hKcXW6OY5s9pRy86y61XE7N1jkhp03Y2RK_UqLCUgQIAZkTXFwAzCC1sVZpZeaqO0BcsyTb1MOveDxG1VpbaKRL90Q";
//        if (fcmToken != null) {
//            List<String> rt = Collections.singletonList(fcmToken);
//            FirebaseMessaging.getInstance()
//                    .subscribeToTopic(rt, encodedTopic);
//        }
//    }

//    @Transactional
//    public void deleteTopic(String nickname, String topic) throws FirebaseMessagingException {
//        String encodedTopic = URLEncoder.encode(topic, StandardCharsets.UTF_8);
//        String fcmToken = getFcmToken(nickname);
//        if (fcmToken != null) {
//            List<String> rt = Collections.singletonList(fcmToken);
//            FirebaseMessaging.getInstance()
//                    .unsubscribeFromTopic(rt, (encodedTopic));
//        }
//    }

//    @Transactional
//    public void sendTopic(String topic) {
//        String encodedTopic = URLEncoder.encode(notificationResponse.getTopic(), StandardCharsets.UTF_8);
//        String sendTitle = notificationResponse.getTopic() + " 키워드 알림이 도착했어요!";
//        String sendMessage = notificationResponse.getTopic() + " 키워드에 해당하는 물품이 등록되었어요!";
//        WebpushNotification webpushNotification = WebpushNotification.builder()
//                .setTitle(sendTitle)
//                .setBody(sendMessage)
//                .setTag(notificationResponse.getProductId().toString())
//                .build();
//
//        WebpushConfig webpushConfig = WebpushConfig.builder()
//                .setNotification(webpushNotification)
//                .build();
//
//        Message message = Message.builder()
//                .setTopic(encodedTopic)
//                .setWebpushConfig(webpushConfig)
//                .putData("productId", notificationResponse.getProductId().toString())
//                .build();
//        FirebaseMessaging.getInstance().sendAsync(message);
//
//        List<String> receivers = getReceiverByKeyword(notificationResponse.getTopic());
//        receivers.forEach(r -> {
//            Notice notice = Notice.builder()
//                    .type("키워드 알림")
//                    .target(notificationResponse.getProductId())
//                    .message(notificationResponse.getTopic() + "키워드 알림이 도착했습니다.")
//                    .build();
//            noticeService.addNotice(notice, r);
//        });
//    }
//
//    @Transactional
//    public List<String> getReceiverByKeyword(String keyword) {
//        List<MemberKeyword> list = memberKeywordRepository.findAllByKeywordKeyword(keyword);
//        return list.stream()
//                .map(m -> m.getMember().getUsername())
//                .collect(Collectors.toList());
//    }
}
