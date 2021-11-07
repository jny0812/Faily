package Project.Projectspring.chatex.Chatting.Service;

import Project.Projectspring.chatex.Chatting.Controller.ChatController;
import Project.Projectspring.chatex.Chatting.VO.ChattingListVO;

import java.util.List;

public interface ChatServicein {

    String getGroupCode(int user_id) throws Exception;

    List<ChattingListVO> ChatList(String group_code) throws Exception;

    void updateIsRead(int user_id) throws Exception;

    int numberOfUnread(ChatController.UnreadListVO unreadListVO) throws Exception;

    String getUserName(int user_id) throws Exception;
}
