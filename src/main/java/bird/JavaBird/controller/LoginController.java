package bird.JavaBird.controller;

import bird.JavaBird.SessionConst;
import bird.JavaBird.aop.Retry;
import bird.JavaBird.domain.Member;
import bird.JavaBird.dto.LoginDto;
import bird.JavaBird.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginHome(@ModelAttribute("loginForm") LoginDto form) {
        return "login";
    }

    @Retry
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginDto form, BindingResult bindingResult,
                        @RequestParam(name = "redirectURL", defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {
        log.info("log in controller");
        if (bindingResult.hasErrors()) {
            return "login";
        }

        Member loginMember = memberService.login(form.getMemberName(),form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        log.info("login success redirect: {}", redirectURL);
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        log.info("logout");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
