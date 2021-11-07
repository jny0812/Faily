package Project.Projectspring.Home.Controller;

import Project.Projectspring.FileByte;
import Project.Projectspring.Home.Service.HomeService;
import Project.Projectspring.Home.VO.*;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HomeApiController {

    private final JoinController joinController;
    private final QuestionService questionService;
    private final HomeService homeService;
    private final FileByte fileByte;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public class imageneedVO{
        private MultipartFile file;
        private int user_id;
    }



    @Getter
    @Setter
    @AllArgsConstructor
    public static class FindNeedVO {
        private int group_id;
        private String calendar_date;
    }



    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeList{
        float group_bonding;
        String user_mood;
        List<FamilyListVO> familyList;
        List<TodayAnniversaryVO> today_anniversary;
        String calendar_date;
        String user_name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class responseAnswer {
        boolean IsSuccess;
        int code;
        String message;
        List<HomeList> result;

    }

    /** 홈 화면 **/
    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    @ResponseBody
    public Object Home() throws Exception {

        String a = joinController.remakeJwtToken();
        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //질문한 user의 group_id 추출
        String user_name = homeService.getUserName(user_id);

        try{
        if(e_mail==null) {

            HashMap<String, Object> result = new HashMap<>();

            result.put("isSuccess", false);
            result.put("code",301);
            result.put("message","유효하지 않은 사용자입니다.");

            return result;
        }
        else {
            responseAnswer response = new responseAnswer(true, 200, "홈 화면", null);

            Date time = new Date();
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(time);

            String calendar_date = sdformat.format(cal.getTime());
//            String today = calendar_date;

            FindNeedVO findNeedVO = new FindNeedVO(group_id, null);

            float group_bonding = homeService.GroupBonding(group_id);
            String user_mood = homeService.getUserMood(user_id);
//            homeLists.setGroup_bonding(group_bonding);

            HomeList homeLists = new HomeList(group_bonding, user_mood, null, null, calendar_date, user_name);

                List<FamilyListNeedVO> familyListNeedVOS = homeService.getFamilyList(group_id);
                List<FamilyListVO> familyListVOS = new ArrayList<>();

                log.warn(String.valueOf(familyListNeedVOS.size()));

            List<HomeList> list = new ArrayList<>();

                for (int j = 0; j < familyListNeedVOS.size(); j++) {

                    FamilyListVO familyListVO = new FamilyListVO(familyListNeedVOS.get(j).getUser_name(),
                            familyListNeedVOS.get(j).getUser_bonding(),
                            fileByte.transferUserFile(familyListNeedVOS.get(j).getUser_image()),
                            familyListNeedVOS.get(j).getUser_mood());

                    familyListVOS.add(familyListVO);
                    homeLists.setFamilyList(familyListVOS);}

                    if (homeService.CheckCalendar(calendar_date) == null) {  //만약 일정이 없으면
                        log.warn(String.valueOf(homeService.CheckCalendar(calendar_date)));
                        log.warn("calendar_date complete");
                        list.add(homeLists);
                    }

                    else {
                        findNeedVO.setCalendar_date(calendar_date);

                        List<GetByGroupIdVO> getByGroupIdVOS = homeService.getByGroupId(findNeedVO);



                        List<TodayAnniversaryVO> today_anniversary = homeService.getCalendar(findNeedVO);
                        log.warn(String.valueOf(today_anniversary.toString()));

//                float group_bonding = getByGroupIdVOS.get(0).getGroup_bonding();
//                getByGroupIdVOS.get(0).
//
//                String today = getByGroupIdVOS.get(0).getCalendar_date();
//                homeLists.setGroup_bonding(group_bonding);
                        homeLists.setToday_anniversary(today_anniversary);
                        list.add(homeLists);
                }
            response.setResult(list);

            return response;}

//                list.add(homeLists);

        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();

            result.put("isSuccess", false);
            result.put("code",301);
            result.put("message","유효하지 않은 사용자입니다.");

            return result;}

}

    @Getter
    @Setter
    @AllArgsConstructor
    public static class testVO {
        private MultipartFile file;
        private int user_id;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ImgVO {
        private MultipartFile file;
        private int emoji_id;

    }


    @PostMapping("/test_img")
    public void image(@ModelAttribute testVO testVO) throws Exception {
        String path = fileByte.saveFile(testVO.getFile());
        log.warn(String.valueOf(path));
//         byte[] file_byte = fileByte.transferUserFile(path);
         putImageVO putImageVO = new putImageVO(path, testVO.getUser_id());
         homeService.putImagePath(putImageVO);
    }

    @PostMapping("/emoji_img")
    public void image(@ModelAttribute ImgVO imgVO) throws Exception {
        String path = fileByte.saveFile(imgVO.getFile());
        log.warn(String.valueOf(path));
        byte[] file_byte = fileByte.transferUserFile(path);
        putEmojiVO putEmojiVO = new putEmojiVO(file_byte,imgVO.getEmoji_id());
        homeService.putEmojibyte(putEmojiVO);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public class putImageVO{
        private String path;
        private int user_id;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public class putEmojiVO{
        private byte[] emoji;
        private int emoji_id;
    }
}
