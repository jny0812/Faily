package Project.Projectspring.Answer.VO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.Calendar;


@Getter
@Setter
@ToString
public class AnswerVO {

    private int answer_id;
    private String answer;
    private int answer_user_id;
    private Date answer_time;
    private int answer_question_id;
    private int answer_status;

}
