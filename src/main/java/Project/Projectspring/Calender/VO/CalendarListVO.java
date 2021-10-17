package Project.Projectspring.Calender.VO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarListVO {

    private int group_id;
    private String calendar_date;
}
