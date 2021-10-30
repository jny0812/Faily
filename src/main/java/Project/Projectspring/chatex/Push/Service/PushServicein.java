package Project.Projectspring.chatex.Push.Service;

import Project.Projectspring.chatex.Push.VO.ChatPutVO;
import Project.Projectspring.chatex.Push.VO.ChatVO;
import Project.Projectspring.chatex.Push.VO.ReceiverListVO;

import java.util.List;

public interface PushServicein {

    String getFcmToken(int user_id) throws Exception;

    List<ReceiverListVO> Receivers(String group_code) throws Exception;

    void putChatting(ChatPutVO chatPutVO) throws Exception;

}
