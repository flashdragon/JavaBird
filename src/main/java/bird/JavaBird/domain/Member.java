package bird.JavaBird.domain;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String memberName;
    private String NickName;
    private String password;
}
