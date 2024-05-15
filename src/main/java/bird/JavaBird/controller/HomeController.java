package bird.JavaBird.controller;

import bird.JavaBird.SessionConst;
import bird.JavaBird.aop.Retry;
import bird.JavaBird.domain.Display;
import bird.JavaBird.domain.Member;
import bird.JavaBird.domain.Post;
import bird.JavaBird.dto.LoginDto;
import bird.JavaBird.service.FollowService;
import bird.JavaBird.service.MemberService;
import bird.JavaBird.service.PostService;
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
    private final PostService postService;
    private final MemberService memberService;

    private final FollowService followService;
    @Retry
    @GetMapping("/")
    public String home(@ModelAttribute("loginForm") LoginDto form, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model, @RequestParam(name = "page", defaultValue = "1") Integer page) {
        log.info("home controller {}", loginMember);
        List<Post> posts = postService.findAll(page);
        List<Display> displays = new ArrayList<>();
        for (Post p : posts) {
            //log.info("{}", p);
            Display d = new Display();
            d.setName(memberService.findById(p.getMemberId()).getNickName());
            d.setPostId(p.getPostId());
            d.setMemberId(p.getMemberId());
            d.setImageFile(p.getImageFile());
            d.setContents(p.getContents());
            if (loginMember != null) {
                d.setFollow(followService.isFollow(loginMember.getId(), p.getMemberId()));
            }
            displays.add(d);
        }
        model.addAttribute("posts", displays);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}
