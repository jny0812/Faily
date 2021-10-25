package Project.Projectspring.chatex.Redis.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisVO {

    private String user_name;
    private String fcm_token;
}
