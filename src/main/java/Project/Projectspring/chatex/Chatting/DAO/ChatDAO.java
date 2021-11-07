package Project.Projectspring.chatex.Chatting.DAO;

import Project.Projectspring.chatex.Chatting.Controller.ChatController;
import Project.Projectspring.chatex.Chatting.VO.ChattingListVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDAO implements ChatDAOin{

    private static final String NAMESPACE = "Project.Projectspring.chatex.Chatting.ChattingMapper";

    private final SqlSession sqlSession;

    @Autowired
    public ChatDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}

    @Override
    public String getGroupCode(int user_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".getGroupCode",user_id);
    }

    @Override
    public List<ChattingListVO> ChatList(String group_code) throws Exception {
        return sqlSession.selectList(NAMESPACE+".ChatList", group_code);
    }

    @Override
    public void updateIsRead(int user_id) throws Exception {
        sqlSession.update(NAMESPACE+".updateIsRead",user_id);
    }

    @Override
    public int numberOfUnread(ChatController.UnreadListVO unreadListVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".numberOfUnread", unreadListVO);
    }

    @Override
    public String getUserName(int user_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".getUserName",user_id);
    }
}
