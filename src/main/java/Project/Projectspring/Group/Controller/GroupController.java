package Project.Projectspring.Group.Controller;

import Project.Projectspring.Group.DAO.GroupDAO;
import Project.Projectspring.Group.Service.GroupService;
import Project.Projectspring.Group.VO.GroupCreateTimeVO;
import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Group.VO.UserGroupVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Question.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    //private final GroupController groupController;

    private final JoinController joinController;
    private final QuestionService questionService;

    private final HttpServletResponse response;
    private UserGroupVO userGroupVO;
    //private final HttpServletRequest request;

    //코드 발급
    @RequestMapping(value = "/GroupCode", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> makeCode() throws Exception {

        //log.warn(String.valueOf() );

        StringBuffer temp = new StringBuffer();
        String group_code = "";
        Random rnd = new Random();
        HashMap<String, Object> result = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));

                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));

                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));

                    break;
            }
            group_code = temp.toString();
        }

            String a = joinController.remakeJwtToken();
            String e_mail = joinController.getSubject(); //user_email 추출
            int user_id = questionService.userIdCheck(e_mail);  //user_id 추출

            if (e_mail == null) {
                result.put("isSuccess", false);
                result.put("code", 301);
                result.put("message", "이미 그룹이 존재합니다.");

            }
            else if(groupService.isExisted(e_mail) == null) {

                Date time = new Date();
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance(); cal.setTime(time);
                cal.add(Calendar.HOUR, 9);
                String group_create_time = sdformat.format(cal.getTime());

                GroupCreateTimeVO groupCreateTimeVO = new GroupCreateTimeVO(group_code,group_create_time);
                groupService.createGroup(groupCreateTimeVO); //group 생성


                int group_id = groupService.groupIdCheck(group_code);

                UserGroupVO userGroupVO = new UserGroupVO(group_id,null, e_mail,group_id);
                groupService.updateUserGroupId(userGroupVO);

                result.put("isSuccess", true);
                result.put("code", 200);
                result.put("message", "코드가 발급되었습니다.");
                result.put("GroupCode", group_code);
                result.put("group_id",group_id);
                result.put("e_mail",e_mail);
            }
            else {

                int group_id = questionService.questionUserGroupId(user_id);

                String created_group_code = groupService.groupCode(group_id);

                result.put("isSuccess", true);
                result.put("code", 200);
                result.put("message", "코드가 발급되었습니다.");
                result.put("GroupCode", created_group_code);

            }

        return result;
    }


    //채팅방 참가
    @ResponseBody
    @RequestMapping(value = "/EntryChat",method = RequestMethod.POST)
    public HashMap<String, Object> EntryChat(@RequestBody GroupVO groupVO) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        //GroupController groupController = new GroupController(groupService);

        if(groupService.codeCheck(groupVO) == null){

            //int group_id = groupService.groupIdCheck(group_code);

            String a = joinController.remakeJwtToken();

            result.put("isSuccess", false);
            result.put("code",404);
            result.put("message","존재하지 않는 채팅방입니다.");

        }
        else{

            String group_code = groupService.codeCheck(groupVO);
            String e_mail = joinController.getSubject();
            int group_id = groupService.groupIdCheck(group_code);

            UserGroupVO userGroupVO = new UserGroupVO(group_id,null, e_mail,group_id);
            groupService.updateUserGroupId(userGroupVO);

            result.put("isSuccess", true);
            result.put("code",200);
            result.put("message","채팅방에 참가하였습니다.");
        }
        return result;
    }


}

