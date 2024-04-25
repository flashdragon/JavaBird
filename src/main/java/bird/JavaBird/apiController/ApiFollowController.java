package bird.JavaBird.apiController;

import bird.JavaBird.SessionConst;
import bird.JavaBird.domain.Member;
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
    public ApiResult<Boolean> following(@PathVariable("memberId") Long memberId, HttpServletRequest request) throws IOException {
        log.info("following");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return success(followService.following(loginMember.getId(), memberId));
    }

    @PostMapping("/unfollow/{memberId}")
    public ApiResult<Boolean> unfollowing(@PathVariable("memberId") Long memberId, HttpServletRequest request) throws IOException {
        log.info("following");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return success(followService.unfollowing(loginMember.getId(), memberId));
    }
}
