package bird.JavaBird.apiController;

import bird.JavaBird.SessionConst;
import bird.JavaBird.controller.LoginForm;
import bird.JavaBird.domain.Display;
import bird.JavaBird.domain.Member;
import bird.JavaBird.domain.Post;
import bird.JavaBird.service.FollowService;
import bird.JavaBird.service.MemberService;
import bird.JavaBird.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiHomeController {
    private final PostService postService;
    private final MemberService memberService;

    private final FollowService followService;
    @GetMapping
    public List<Display> home(@ModelAttribute("loginForm") LoginForm form, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        log.info("home controller {}", loginMember);
        List<Post> posts = postService.findAll();
        List<Display> displays = new ArrayList<>();
        for (Post p : posts) {
            log.info("{}", p);
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
        return displays;
    }

}
