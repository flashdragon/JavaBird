package bird.JavaBird.apiController;

import bird.JavaBird.SessionConst;
import bird.JavaBird.domain.Member;
import bird.JavaBird.dto.LoginInfoDto;
import bird.JavaBird.security.login.Login;
import bird.JavaBird.service.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import static bird.JavaBird.utils.ApiUtils.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiFollowController {

    private final FollowService followService;


    @PostMapping("/follow/{memberId}")
    public ApiResult<Boolean> following(@PathVariable("memberId") Long memberId, @Login LoginInfoDto loginMember) throws IOException {
        log.info("follow api controller {}", loginMember);

        return success(followService.following(loginMember.getMemberId(), memberId));
    }

    @PostMapping("/unfollow/{memberId}")
    public ApiResult<Boolean> unfollowing(@PathVariable("memberId") Long memberId, @Login LoginInfoDto loginMember) throws IOException {
        log.info("unfollow api controller {}", loginMember);

        return success(followService.unfollowing(loginMember.getMemberId(), memberId));
    }
}
