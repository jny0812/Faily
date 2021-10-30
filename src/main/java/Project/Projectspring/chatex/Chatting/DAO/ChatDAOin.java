package Project.Projectspring.chatex.Chatting.DAO;

import Project.Projectspring.chatex.Chatting.VO.ChattingListVO;

import java.util.List;

public interface ChatDAOin {

    String getGroupCode(int user_id) throws Exception;

    List<ChattingListVO> ChatList(String group_code) throws Exception;

    void updateIsRead(int user_id) throws Exception;

    int numberOfUnread(ChattingListVO chattingListVO) throws Exception;

    String getUserName(int user_id) throws Exception;
}
