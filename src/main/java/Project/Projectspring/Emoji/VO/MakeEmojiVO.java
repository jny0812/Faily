package Project.Projectspring.Emoji.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MakeEmojiVO {

//    private int emoji_id;
    private byte [] emoji;
    private int user_id;
    private int group_id;
    private String emoji_time;
}
