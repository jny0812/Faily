package Project.Projectspring.Calender.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CalenderVO {

    private String calender_date;
    private String calender_category;
    private String calender_name;
    private String calender_place;
    private String calender_memo;
    private String calender_start_time;
    private String calender_end_time;
    //private int calender_user_id;
//    private int calender_group_id;
}
