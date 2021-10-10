package Project.Projectspring.Photo.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
public class PhotoCategoryVO {

    private int photo_category_id;
    private String photo_category_name;
    private int user_id;
    private String user_email;
    private String user_name;

    public PhotoCategoryVO(int photo_category_id, String photo_category_name, int user_id, String user_email, String user_name) {
        this.photo_category_id = photo_category_id;
        this.photo_category_name = photo_category_name;
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_name = user_name;
    }
}
