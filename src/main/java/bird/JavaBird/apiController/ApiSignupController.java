package bird.JavaBird.apiController;

import bird.JavaBird.controller.SignUpForm;
import bird.JavaBird.domain.Member;
import bird.JavaBird.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiSignupController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseJson addMember(@Valid @ModelAttribute("signupForm") SignUpForm form) {
        log.info("post signup name={} password={} nickname={}", form.getMemberName(), form.getPassword(), form.getNickName());
        Member member = new Member(form.getMemberName(), form.getPassword(), form.getNickName());
        memberService.save(member);
        ResponseJson responseJson = new ResponseJson();
        responseJson.setCode(200);
        responseJson.setMessage("success");
        return responseJson;
    }

}
