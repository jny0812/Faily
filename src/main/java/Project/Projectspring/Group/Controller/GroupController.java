package Project.Projectspring.Group.Controller;

import Project.Projectspring.Group.Service.GroupService;
import Project.Projectspring.Group.VO.GroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Random;

@RestController
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {this.groupService = groupService;}


    @RequestMapping(value = "/GroupCode", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> makeCode(@RequestBody GroupVO groupVO)throws Exception{

        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        HashMap<String, Object> result = new HashMap<>();

        for (int i = 0; i < 20; i++) {
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

            try{
                groupService.createGroup(groupVO);

                result.put("isSuccess",true);
                result.put("code", 200);
                result.put("message","코드가 발급되었습니다.");
                result.put("GroupCode",temp.toString());


            } catch (Exception e) {
                result.put("isSuccess",false);
                result.put("code",404);
                result.put("message","네트워크 오류");
            }

        }
        return result;
    }
}
