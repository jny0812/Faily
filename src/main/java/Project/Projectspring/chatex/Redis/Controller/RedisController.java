package Project.Projectspring.chatex.Redis.Controller;

import Project.Projectspring.chatex.Redis.Service.RedisService;
import Project.Projectspring.chatex.Redis.VO.RedisVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping(value = "/getRedisStringValue")
    public void getRedisStringValue(@RequestBody int user_id ) {
        redisService.getRedisStringValue(user_id);
    }

    @PostMapping(value = "/setRedisStringValue")
    public void setRedisStringValue(@RequestBody RedisVO redisVO) {
        redisService.setRedisStringValue(redisVO.getUser_name(), redisVO.getFcm_token());
    }

}
