package Project.Projectspring.Answer.Service;

public interface IsAnsweredServicein {

    int checkUserStatus(String e_mail) throws Exception;

    int answeredUserGroupId(String e_mail) throws Exception;

    int answeredUserNumber(int user_group_id) throws Exception;

    int userNumber(int user_group_id) throws Exception;

    String userName(String e_mail) throws Exception;

    Integer isAnsweredUser(int question_id) throws Exception;

}
