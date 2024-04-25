package bird.JavaBird.apiController;

import bird.JavaBird.dto.SignUpDto;
import bird.JavaBird.domain.Member;
import bird.JavaBird.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static bird.JavaBird.utils.ApiUtils.*;
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiSignupController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResult<Member> addMember(@Valid @ModelAttribute("signupForm") SignUpDto form) {
        log.info("post signup name={} password={} nickname={}", form.getMemberName(), form.getPassword(), form.getNickName());
        Member member = new Member(form.getMemberName(), form.getPassword(), form.getNickName());
        return success(memberService.save(member));
    }

}
