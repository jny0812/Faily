package Project.Projectspring.Question.DAO;


import Project.Projectspring.Question.VO.AllQuestionsVO;
import Project.Projectspring.Question.VO.GroupQuestionVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QuestionDAO implements QuestionDAOin{

    private static final String NAMESPACE = "Project.Projectspring.Question.QuestionMapper";

    private final SqlSession sqlSession;

    @Autowired
    public QuestionDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}

    @Override
    public String questionNumberCheck(int question_number) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".questionNumberCheck",question_number);
    }

    @Override
    public int userIdCheck(String e_mail) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".userIdCheck",e_mail);
    }

    @Override
    public void statusChangeToZero(int user_id) throws Exception {
        sqlSession.update(NAMESPACE+".statusChangeToZero",user_id);
    }


    @Override
    public int questionId(String question) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".questionId",question);
    }

    @Override
    public int questionUserGroupId(int user_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".questionUserGroupId",user_id);
    }

    @Override
    public void createGroupQuestion(GroupQuestionVO groupQuestionVO) throws Exception {
        sqlSession.insert(NAMESPACE+".createGroupQuestion", groupQuestionVO);
    }

    @Override
    public List<AllQuestionsVO> allQuestion(int group_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".allQuestion",group_id);
    }

    @Override
    public String bringQuestion(int group_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".bringQuestion",group_id);
    }

    @Override
    public void ChangeGroupQuestion(int group_id) throws Exception {
        sqlSession.update(NAMESPACE + ".ChangeGroupQuestion", group_id);
    }

    @Override
    public String questionTime(int group_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".questionTime",group_id);
    }

    @Override
    public List<AllQuestionsVO> GroupquestionAnswer(int question_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".questionTime",question_id);
    }

    @Override
    public List<AllQuestionsVO> UserAnswer(int answer_group_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".UserAnswer",answer_group_id);
    }

    @Override
    public List<Map<String, Object>> selectQuestions(int group_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".quesitongroupuser",group_id);
    }

    @Override
    public int isAnsweredUser(int question_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".isAnsweredUser",question_id);
    }

}
