package Project.Projectspring.chatex.Chatting.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingListVO {

    private String sender_name;
    private String content;
    private byte[] chatting_file;
    private String group_code;
    private String chatting_time;
    private boolean emoji;
    private boolean photo;
    private boolean calendar;
    private boolean text;

}
