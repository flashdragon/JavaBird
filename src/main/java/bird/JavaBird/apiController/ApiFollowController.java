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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiFollowController {

    private final FollowService followService;

    @PostMapping("/follow/{memberId}")
    public ResponseJson following(@PathVariable("memberId") Long memberId, HttpServletRequest request) throws IOException {
        log.info("following");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        followService.following(loginMember.getId(), memberId);
        ResponseJson responseJson = new ResponseJson();
        responseJson.setCode(200);
        responseJson.setMessage("success");
        return responseJson;
    }

    @PostMapping("/unfollow/{memberId}")
    public ResponseJson unfollowing(@PathVariable("memberId") Long memberId, HttpServletRequest request) throws IOException {
        log.info("following");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        followService.unfollowing(loginMember.getId(), memberId);
        ResponseJson responseJson = new ResponseJson();
        responseJson.setCode(200);
        responseJson.setMessage("success");
        return responseJson;
    }
}
