package Project.Projectspring.chatex.Push.Service;

import Project.Projectspring.chatex.Push.VO.ChatVO;
import Project.Projectspring.chatex.Push.VO.ReceiverListVO;

import java.util.List;

public interface PushServicein {

    int getFcmToken(String user_name) throws Exception;

    List<ReceiverListVO> Receivers(String group_code) throws Exception;

    void putChatting(ChatVO chatVO) throws Exception;

    int findIdbyName(String sender_name) throws Exception;
}
