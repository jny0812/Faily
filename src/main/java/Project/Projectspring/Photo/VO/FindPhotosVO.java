package Project.Projectspring.Photo.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPhotosVO {

    private byte [] photo;
    private String sender_name;
    private String time;
}
