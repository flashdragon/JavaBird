package bird.JavaBird.apiController;

import bird.JavaBird.SessionConst;
import bird.JavaBird.dto.LoginDto;
import bird.JavaBird.domain.Display;
import bird.JavaBird.domain.Member;
import bird.JavaBird.domain.Post;
import bird.JavaBird.dto.LoginInfoDto;
import bird.JavaBird.security.login.Login;
import bird.JavaBird.service.FollowService;
import bird.JavaBird.service.MemberService;
import bird.JavaBird.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static bird.JavaBird.utils.ApiUtils.*;
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiHomeController {
    private final PostService postService;
    private final MemberService memberService;

    private final FollowService followService;
    @GetMapping
    public ApiResult<List<Display>> home(@ModelAttribute("loginForm") LoginDto form, @Login LoginInfoDto loginMember, Model model) {
        log.info("{}",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        log.info("home api controller {}", loginMember);
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
                d.setFollow(followService.isFollow(loginMember.getMemberId(), p.getMemberId()));
            }
            displays.add(d);
        }
        return success(displays);
    }

}
