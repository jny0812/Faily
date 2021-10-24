package Project.Projectspring.chatex.Redis.Controller;

import Project.Projectspring.chatex.Redis.Service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping(value = "/getRedisStringValue")
    public void getRedisStringValue(String key) {
        redisService.getRedisStringValue(key);
    }

}
