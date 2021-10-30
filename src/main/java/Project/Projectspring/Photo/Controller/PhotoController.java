package Project.Projectspring.Photo.Controller;

import Project.Projectspring.Calender.VO.CalenderVO;
import Project.Projectspring.Home.Controller.HomeApiController;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Photo.Service.PhotoService;
import Project.Projectspring.Photo.Service.PhotoServicein;
import Project.Projectspring.Photo.VO.CreatePhotoCategoryVO;
import Project.Projectspring.Photo.VO.FindPhotosVO;
import Project.Projectspring.Question.Service.QuestionService;
import Project.Projectspring.chatex.Chatting.Service.ChatService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PhotoController {

    private final JoinController joinController;
    private final QuestionService questionService;
    private final ChatService chatService;
    private final PhotoService photoService;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Findphoto2VO {
        private byte [] photo;
        private String sender_name;
        private String time;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class responseAnswer {
        boolean IsSuccess;
        int code;
        String message;
        PhotoController.Findphoto2VO result;

    }

    /**최근 항목 불러오기 (전체 사진 불러오기)**/
    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    @ResponseBody
    public Object photos() throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //group_id 추출
        String group_code = chatService.getGroupCode(user_id); //group_code 추출
        String sender_name = chatService.getUserName(user_id);

        try{

            PhotoController.responseAnswer response = new PhotoController.responseAnswer(true, 200, "최근 사진 목록", null);

            List<FindPhotosVO> findPhotosVOS = photoService.Photos(group_code);

            for(int i=0;i<findPhotosVOS.size();i++) {
                Findphoto2VO findphoto2VO = new Findphoto2VO();
                findphoto2VO.setPhoto(findPhotosVOS.get(i).getPhoto());
                findphoto2VO.setSender_name(findPhotosVOS.get(i).getSender_name());
                findphoto2VO.setTime(findPhotosVOS.get(i).getTime());

                response.setResult(findphoto2VO);
            }

            return response;

        }
        catch (Exception e){
            e.printStackTrace();
            log.warn(String.valueOf(e));

                result.put("isSuccess", false);
                result.put("code",301);
                result.put("message","유효하지 않은 사용자입니다.");

            return result;
        }
    }

    /** 포토 카테고리 추가 API **/  //카테고리는 사용자마다 다름

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckPhotoCateVO {
        private String photo_category;
        private int user_id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhotoCategoryNameVO {
        private String photo_category;
    }


    @RequestMapping(value = "/photoCategory", method = RequestMethod.POST)
    @ResponseBody
    public Object createPhotoCategory(@RequestBody PhotoCategoryNameVO photoCategoryNameVO) throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //group_id 추출
        String group_code = chatService.getGroupCode(user_id); //group_code 추출

        try{

            //카테고리 이미 존재하는 경우
            CheckPhotoCateVO checkPhotoCateVO = new CheckPhotoCateVO(photoCategoryNameVO.getPhoto_category(),user_id);

            if(photoService.checkPhotoCategory(checkPhotoCateVO) != null) {
                result.put("isSuccess", false);
                result.put("code",302);
                result.put("message","이미 존재하는 카테고리입니다.");
                log.warn("이미존재warning");

                return result;
            }

        CreatePhotoCategoryVO createPhotoCategoryVO = new CreatePhotoCategoryVO(photoCategoryNameVO.getPhoto_category(),user_id,group_id);
        photoService.createPhotoCategory(createPhotoCategoryVO);

        result.put("isSuccess",true);
        result.put("code",200);
        result.put("message", "카테고리가 추가되었습니다");
        result.put("photo_category",photoCategoryNameVO.getPhoto_category());
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            result.put("isSuccess", false);
            result.put("code",301);
            result.put("message","유효하지 않은 사용자입니다.");
        }
        return result;
    }

    /** 포토 bookmark (즐겨찾기) API **/

    /** 카테고리에 포토 추가 API **/

}
