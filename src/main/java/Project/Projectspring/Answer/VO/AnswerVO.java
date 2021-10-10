package Project.Projectspring.Answer.VO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Date;


@Getter
@Setter
@ToString
public class AnswerVO {

    private int answer_id;
    private String answer;
    private int user_id;
    private String answer_time;
    private int answer_question_id;
    private int answer_status;
    private int group_id;

    public AnswerVO(int answer_id, String answer, int user_id, String
            answer_time, int answer_question_id, int answer_status,int group_id) {
        this.answer_id = answer_id;
        this.answer = answer;
        this.user_id = user_id;
        this.answer_time = answer_time;
        this.answer_question_id = answer_question_id;
        this.answer_status = answer_status;
        this.group_id = group_id;
    }
}
