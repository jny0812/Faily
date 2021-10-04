package Project.Projectspring.Question.Service;


public interface QuestionServicein {

    String questionNumberCheck(int question_number) throws Exception;

    int userIdCheck(String e_mail) throws Exception;

    void statusChangeToZero(int user_id) throws Exception;
}
