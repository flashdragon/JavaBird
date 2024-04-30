package bird.JavaBird.apiController;

import bird.JavaBird.dto.LoginDto;
import bird.JavaBird.domain.Member;
import bird.JavaBird.dto.LoginResponseDto;
import bird.JavaBird.exception.LoginException;
import bird.JavaBird.security.JwtTokenizer;
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


import static bird.JavaBird.utils.ApiUtils.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiLoginController {
    private final MemberService memberService;


    private final JwtTokenizer jwtTokenizer;

    @PostMapping("/login")
    public ApiResult<LoginResponseDto> login(@Valid @ModelAttribute("loginForm") LoginDto form, BindingResult bindingResult,
                                   HttpServletRequest request) {
        log.info("log in api controller");
        if (bindingResult.hasErrors()) {
            throw new LoginException("잘못된 사용자");
        }

        Member loginMember = memberService.login(form.getMemberName(),form.getPassword());
        String token = jwtTokenizer.createToken(loginMember.getId(), loginMember.getMemberName());


        return success(new LoginResponseDto(loginMember, token));

    }

}
