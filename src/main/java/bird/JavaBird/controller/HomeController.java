package bird.JavaBird.controller;

import bird.JavaBird.domain.Member;
import bird.JavaBird.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    @GetMapping("/")
    public String home(@ModelAttribute("loginForm") LoginForm form) {
        log.info("home controller");
        return "home";
    }
    @GetMapping("/signup")
    public String signUp(@ModelAttribute("signupForm")SignUpForm form) {
        log.info("signup controller");
        return "signUp";
    }
    @PostMapping("/signup")
    public String addMember(@ModelAttribute("signupForm")SignUpForm form) {
        log.info("post signup name={} password={} nickname={}", form.getMemberName(), form.getPassword(), form.getNickName());
        Member member = new Member();
        member.setMemberName(form.getMemberName());
        member.setNickName(form.getNickName());
        member.setPassword(form.getPassword());
        memberRepository.save(member);
        return "redirect:/";
    }
}
