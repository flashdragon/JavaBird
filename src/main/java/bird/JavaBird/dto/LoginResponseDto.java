package bird.JavaBird.dto;

import bird.JavaBird.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginResponseDto {
    @NotEmpty
    private String memberName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String token;

    public LoginResponseDto(Member member, String token) {
        this.memberName = member.getMemberName();
        this.password = member.getPassword();
        this.token = token;
    }
}
