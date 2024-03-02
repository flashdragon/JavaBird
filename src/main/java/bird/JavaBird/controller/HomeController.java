package bird.JavaBird.controller;

import bird.JavaBird.SessionConst;
import bird.JavaBird.domain.Member;
import bird.JavaBird.domain.Post;
import bird.JavaBird.repository.MemberRepository;
import bird.JavaBird.repository.PostRepository;
import bird.JavaBird.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    private final FollowService followService;
    @GetMapping("/")
    public String home(@ModelAttribute("loginForm") LoginForm form, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        log.info("home controller {}", loginMember);
        List<Post> posts = postRepository.findAll();
        for (Post p : posts) {
            p.setName(memberRepository.findById(p.getMemberId()).getNickName());
            if (loginMember != null) {
                p.setFollow(followService.isFollow(loginMember.getId(), p.getMemberId()));
            }
        }
        model.addAttribute("posts", posts);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}
