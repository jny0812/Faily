package Project.Projectspring.Question.DAO;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


//    @Override
//    public ItemVO read(int item_id) throws Exception {
//        return sqlSession.selectOne(NAMESPACE+".read",item_id);
//    }
}
