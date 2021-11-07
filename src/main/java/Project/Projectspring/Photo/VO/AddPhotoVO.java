package Project.Projectspring.Photo.VO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPhotoVO {

    private int photo_id;
    private String photo_category;
}
