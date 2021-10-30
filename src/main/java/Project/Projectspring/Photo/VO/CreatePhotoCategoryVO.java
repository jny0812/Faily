package Project.Projectspring.Photo.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePhotoCategoryVO {

    private String photo_category;
    private int user_id;
    private int group_id;
}
