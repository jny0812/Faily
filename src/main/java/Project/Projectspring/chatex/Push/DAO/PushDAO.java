package Project.Projectspring.chatex.Push.DAO;

import Project.Projectspring.chatex.Push.VO.ChatVO;
import Project.Projectspring.chatex.Push.VO.ReceiverListVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PushDAO implements PushDAOin {

    private static final String NAMESPACE = "Project.Projectspring.chatex.Push.PushMapper";

    private final SqlSession sqlSession;

    @Autowired
    public PushDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}

    @Override
    public int getFcmToken(String user_name) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".getFcmToken", user_name);
    }

    @Override
    public List<ReceiverListVO> Receivers(String group_code) throws Exception {
        return sqlSession.selectList(NAMESPACE+".Receivers",group_code);
    }

    @Override
    public void putChatting(ChatVO chatVO) throws Exception {
        sqlSession.insert(NAMESPACE+".putChatting", chatVO);
    }

    @Override
    public int findIdbyName(String sender_name) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".findIdbyName",sender_name);
    }
}
