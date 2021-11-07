package Project.Projectspring.Emoji.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetEmojiVO {

    private byte [] emoji;
//    private String emoji_sender_name;
    private String emoji_time;
    private int emoji_id;
}
