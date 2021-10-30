package Project.Projectspring.chatex.Push.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatPutVO {

    private String content;
    private int sender_id;
    private String sender_name;
    private String group_code;
    private int receiver_id;
    private String chatting_time;
//    private String path;
    private byte[] file;
    private boolean emoji_bool;
    private boolean photo_bool;
    private boolean text_bool;
    private boolean calendar_bool;
}
