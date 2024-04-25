package bird.JavaBird.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {

    @NotEmpty
    private String memberName;
    @NotEmpty
    private String password;
}
