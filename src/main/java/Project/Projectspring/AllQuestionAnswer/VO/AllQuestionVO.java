package Project.Projectspring.AllQuestionAnswer.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AllQuestionVO {

  private List<Boolean> isAnswered;
  private List<Boolean> allAnswered;
  private List<AllAnswerVO> allAnswerVOS;
  private List<QuestionListVO> questionListVOS;
// private  ArrayList<List<Object>>  questionAnswerListVOS;

}
