package Project.Projectspring.Group.Controller;

import Project.Projectspring.Group.DAO.GroupDAO;
import Project.Projectspring.Group.Service.GroupService;
import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Group.VO.UserGroupVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Join.VO.JoinVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Random;

//@Headers("Content-Type: application/x-www-form-urlencoded")
@RestController
@Slf4j
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    //private final GroupController groupController;

    private final JoinController joinController;

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

        try {
            groupService.createGroup(group_code);


            String a = joinController.remakeJwtToken();

            int group_id = groupService.groupIdCheck(group_code);

            String e_mail = joinController.getSubject();
            UserGroupVO userGroupVO = new UserGroupVO(group_id,null, e_mail,group_id);
            groupService.updateUserGroupId(userGroupVO);

            result.put("isSuccess", true);
            result.put("code", 200);
            result.put("message", "코드가 발급되었습니다.");
            result.put("GroupCode", group_code);
            result.put("group_id",group_id);
            result.put("e_mail",e_mail);


        } catch (Exception e) {
            result.put("isSuccess", false);
            result.put("code", 404);
            result.put("message", "네트워크 오류");
        }

        return result;
    }

//    public static void main(String[] group_code) {
//        System.out.println("group_code = " + group_code);
//    }

    //채팅방 참가
    @ResponseBody
    @RequestMapping(value = "/EntryChat",method = RequestMethod.POST)
    public HashMap<String, Object> EntryChat(@RequestBody GroupVO groupVO) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        //GroupController groupController = new GroupController(groupService);

        if(groupService.codeCheck(groupVO) == null){

            String a = joinController.remakeJwtToken();

            result.put("isSuccess", false);
            result.put("code",404);
            result.put("message","존재하지 않는 채팅방입니다.");

        }
        else{
            result.put("isSuccess", true);
            result.put("code",200);
            result.put("message","채팅방에 참가하였습니다.");
        }
        return result;
    }
}

