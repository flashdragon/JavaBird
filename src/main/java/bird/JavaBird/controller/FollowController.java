package bird.JavaBird.controller;

import bird.JavaBird.SessionConst;
import bird.JavaBird.domain.Member;
import bird.JavaBird.service.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{memberId}")
    public String following(@PathVariable("memberId") Long memberId, HttpServletRequest request) throws IOException {
        log.info("following");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        followService.following(loginMember.getId(), memberId);

        return "redirect:/";
    }

    @PostMapping("/unfollow/{memberId}")
    public String unfollowing(@PathVariable("memberId") Long memberId, HttpServletRequest request) throws IOException {
        log.info("following");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        followService.unfollowing(loginMember.getId(), memberId);

        return "redirect:/";
    }
}
