package Project.Projectspring.Emoji.Controller;

import Project.Projectspring.Emoji.Service.EmojiService;
import Project.Projectspring.Emoji.VO.GetEmojiVO;
import Project.Projectspring.Emoji.VO.MakeEmojiVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Photo.Controller.PhotoController;
import Project.Projectspring.Photo.VO.CreatePhotoCategoryVO;
import Project.Projectspring.Question.Service.QuestionService;
import Project.Projectspring.chatex.Chatting.Service.ChatService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EmojiController {

    private final JoinController joinController;
    private final QuestionService questionService;
    private final ChatService chatService;
    private final EmojiService emojiService;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class responseAnswer {
        boolean IsSuccess;
        int code;
        String message;
        List<GetEmoji> result;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetEmoji {
        private byte [] emoji;
//        private String sender_name;
        private String time;
        private int emoji_id;
    }

    /**이모티콘 불러오기 (조건: 같은 그룹) **/
    @RequestMapping(value = "/emojis", method = RequestMethod.GET)
    @ResponseBody
    public Object emojis() throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //group_id 추출
        String group_code = chatService.getGroupCode(user_id); //group_code 추출
        String sender_name = chatService.getUserName(user_id);

        try {
            responseAnswer response = new responseAnswer(true, 200, "이모티콘 목록", null);

            List<GetEmojiVO> getEmojiVOS = emojiService.getEmoji(group_id);
            log.warn(String.valueOf(getEmojiVOS.size()));

            List<GetEmoji> list = new ArrayList<>();

            for(int i=0;i<getEmojiVOS.size();i++) {
                GetEmoji getEmoji = new GetEmoji();

                getEmoji.setEmoji(getEmojiVOS.get(i).getEmoji());
//                getEmoji.setSender_name(getEmojiVOS.get(i).getEmoji_sender_name());
                getEmoji.setTime(getEmojiVOS.get(i).getEmoji_time());
                getEmoji.setEmoji_id(getEmojiVOS.get(i).getEmoji_id());
                list.add(getEmoji);

            }
            response.setResult(list);

            return response;

        } catch (Exception e){
            e.printStackTrace();
            log.warn(String.valueOf(e));

            result.put("isSuccess", false);
            result.put("code",301);
            result.put("message","유효하지 않은 사용자입니다.");

            return result;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MakeEmoji {
        private byte [] emoji;
//        private String sender_name;
//        private String time;
//        private int emoji_id;
    }

    /** 이모티콘 추가 **/
    @RequestMapping(value = "/add/emoji", method = RequestMethod.POST)
    @ResponseBody
    public Object addEmoji(@RequestBody MakeEmoji makeEmoji) throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //group_id 추출
        String group_code = chatService.getGroupCode(user_id); //group_code 추출

        Date time = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);

        String emoji_time = sdformat.format(cal.getTime());

        try{

            //카테고리 이미 존재하는 경우
//            PhotoController.CheckPhotoCateVO checkPhotoCateVO = new PhotoController.CheckPhotoCateVO(photoCategoryNameVO.getPhoto_category(),user_id);
//
//            if(photoService.checkPhotoCategory(checkPhotoCateVO) != null) {
//                result.put("isSuccess", false);
//                result.put("code",302);
//                result.put("message","이미 존재하는 카테고리입니다.");
//                log.warn("이미존재warning");
//
//                return result;
//            }


            MakeEmojiVO makeEmojiVO = new MakeEmojiVO(makeEmoji.getEmoji(),user_id,group_id,emoji_time);
            emojiService.makeEmoji(makeEmojiVO);

            emojiService.updateUserBondingByEmoji(user_id);  //+0.1

            result.put("isSuccess",true);
            result.put("code",200);
            result.put("message", "이모티콘이 추가되었습니다");
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            result.put("isSuccess", false);
            result.put("code",301);
            result.put("message","유효하지 않은 사용자입니다.");
        }
        return result;
    }


    /**이모티콘 삭제하기**/

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteEmoji {
//        private byte [] emoji;
//        private String sender_name;
//        private String time;
        private int emoji_id;
    }

    @RequestMapping(value = "/delete/emoji", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteEmoji(@RequestBody DeleteEmoji deleteEmoji) throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        try{
        int emoji_id = deleteEmoji.getEmoji_id();

        emojiService.deleteEmoji(emoji_id);

        result.put("isSuccess",true);
        result.put("code",200);
        result.put("message", "이모티콘이 삭제되었습니다");}
        catch (Exception e) {
            log.warn(String.valueOf(e));
            result.put("isSuccess", false);
            result.put("code",301);
            result.put("message","유효하지 않은 사용자입니다.");
        }
        return result;
    }
}
