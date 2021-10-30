package Project.Projectspring.chatex.Chatting.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingListVO {

    private int sender_id;
    private String content;
    private String chatting_file;
    private String group_code;
    private String chatting_time;

}
