package Project.Projectspring.Photo.VO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePhotoVO {

    private byte [] chatting_file;
    private int sender_id;
    private String sender_name;
    private String group_code;
    private int group_id;
    private String chatting_time;
}
