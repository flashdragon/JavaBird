package bird.JavaBird.controller;

import bird.JavaBird.SessionConst;
import bird.JavaBird.domain.Member;
import bird.JavaBird.domain.Post;
import bird.JavaBird.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final PostRepository postRepository;
    @GetMapping("/")
    public String home(@ModelAttribute("loginForm") LoginForm form, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        log.info("home controller");
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        if (loginMember == null) {
            log.info("model={}",model);
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}
