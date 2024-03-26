package bird.JavaBird.apiController;

import bird.JavaBird.SessionConst;
import bird.JavaBird.controller.LoginForm;
import bird.JavaBird.domain.Member;
import bird.JavaBird.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiLoginController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseJson login(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
                              HttpServletRequest request) {
        log.info("log in controller");
        if (bindingResult.hasErrors()) {
            ResponseJson loginJson = new ResponseJson();
            loginJson.setCode(400);
            loginJson.setMessage("아이디 또는 비밀번호가 맞지 않습니다.");
            return loginJson;
        }

        Member loginMember = memberService.login(form.getMemberName(),form.getPassword());

        if (loginMember == null) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setCode(400);
            responseJson.setMessage("아이디 또는 비밀번호가 맞지 않습니다.");
            return responseJson;
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        log.info("login success api");
        ResponseJson responseJson = new ResponseJson();
        responseJson.setCode(200);
        responseJson.setMessage("success");
        responseJson.setMember(loginMember);
        return responseJson;
    }

    @PostMapping("/logout")
    public ResponseJson logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        ResponseJson responseJson = new ResponseJson();
        responseJson.setMessage("success");
        responseJson.setCode(200);
        return responseJson;
    }

}
