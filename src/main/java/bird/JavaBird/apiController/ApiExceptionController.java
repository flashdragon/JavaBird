package bird.JavaBird.apiController;

import bird.JavaBird.exception.LoginException;
import bird.JavaBird.exception.PostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static bird.JavaBird.utils.ApiUtils.*;

@Slf4j
@RestControllerAdvice("bird.JavaBird.apiController")
public class ApiExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginException.class)
    public ApiResult loginExHandler(LoginException e) {
        log.error("[exceptionHandle] ex", e);
        return error(e, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PostException.class)
    public ApiResult postExHandler(PostException e) {
        log.error("[exceptionHandle] ex", e);
        return error(e, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ApiResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return error(e, HttpStatus.BAD_REQUEST);
    }
}
