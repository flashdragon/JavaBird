package bird.JavaBird.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpForm {

    @NotEmpty
    private String memberName;
    @NotEmpty
    private String nickName;
    @NotEmpty
    private String password;
}
