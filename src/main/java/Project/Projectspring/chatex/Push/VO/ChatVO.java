package Project.Projectspring.chatex.Push.VO;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatVO {

    private String content;
    private int sender_id;
    private String sender_name;
    private String group_code;
    private int receiver_id;
    private String chatting_time;
}
