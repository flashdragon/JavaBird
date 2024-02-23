package bird.JavaBird.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty
    private String memberName;
    @NotEmpty
    private String password;
}
