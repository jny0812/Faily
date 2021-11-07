package Project.Projectspring.chatex.Chatting.Service;

import Project.Projectspring.chatex.Chatting.Controller.ChatController;
import Project.Projectspring.chatex.Chatting.DAO.ChatDAO;
import Project.Projectspring.chatex.Chatting.VO.ChattingListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService implements ChatServicein{

    private final ChatDAO chatDAO;

    @Override
    public String getGroupCode(int user_id) throws Exception {
        return chatDAO.getGroupCode(user_id);
    }

    @Override
    public List<ChattingListVO> ChatList(String group_code) throws Exception {
        return chatDAO.ChatList(group_code);
    }

    @Override
    public void updateIsRead(int user_id) throws Exception {
        chatDAO.updateIsRead(user_id);
    }

    @Override
    public int numberOfUnread(ChatController.UnreadListVO unreadListVO) throws Exception {
        return chatDAO.numberOfUnread(unreadListVO);
    }

    @Override
    public String getUserName(int user_id) throws Exception {
        return chatDAO.getUserName(user_id);
    }
}
