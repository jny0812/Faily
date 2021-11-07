package Project.Projectspring.Emoji.DAO;

import Project.Projectspring.Emoji.VO.CreateEmojiVO;
import Project.Projectspring.Emoji.VO.GetEmojiVO;
import Project.Projectspring.Emoji.VO.MakeEmojiVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmojiDAO implements EmojiDAOin{


    private static final String NAMESPACE = "Project.Projectspring.Emoji.EmojiMapper";

    private final SqlSession sqlSession;

    @Autowired
    public EmojiDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}


    @Override
    public void createEmoji(CreateEmojiVO createEmojiVO) throws Exception {
        sqlSession.insert(NAMESPACE+".createEmoji",createEmojiVO);
    }

    @Override
    public List<GetEmojiVO> getEmoji(int group_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".getEmoji",group_id);
    }

    @Override
    public void makeEmoji(MakeEmojiVO makeEmojiVO) throws Exception {
        sqlSession.insert(NAMESPACE+".makeEmoji",makeEmojiVO);
    }

    @Override
    public void deleteEmoji(int emoji_id) throws Exception {
        sqlSession.delete(NAMESPACE+".deleteEmoji", emoji_id);
    }

    @Override
    public void updateUserBondingByEmoji(int user_id) throws Exception {
        sqlSession.update(NAMESPACE+".updateUserBondingByEmoji",user_id);
    }
}
