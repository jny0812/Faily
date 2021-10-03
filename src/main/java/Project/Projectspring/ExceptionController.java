package Project.Projectspring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "Project.Projectspring")
public class ExceptionController {

    /**
     * 특정 글 조회 시 없는 아이디를 지면 nullpointerexception이 난다.
     */

    @Getter
    @AllArgsConstructor

    class NotExistDataResultResponse<T> {
        private final Boolean isSuccess = false;
        int StatusCode;
        String Message;
    }

//        log.error("[오류가 난 위치를 넣을 수는 없을까...]");

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public NotExistDataResultResponse MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("\n** error occurred!!!!\n[ " + String.valueOf(e));
        return new NotExistDataResultResponse(400, "잘못된 이메일 형식입니다.");
    }}