package bird.JavaBird.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpDto {

    @NotEmpty
    private String memberName;
    @NotEmpty
    private String nickName;
    @NotEmpty
    private String password;
}
