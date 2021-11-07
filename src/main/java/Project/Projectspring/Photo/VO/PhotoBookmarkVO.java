package Project.Projectspring.Photo.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoBookmarkVO {

    private int photo_id;
    private int user_id;
    private int group_id;
}
