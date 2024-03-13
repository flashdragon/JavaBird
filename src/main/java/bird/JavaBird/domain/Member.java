package bird.JavaBird.domain;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String memberName;
    private String password;
    private String nickName;
    public Member() {

    }
    public Member(String memberName, String password, String nickName) {
        this.memberName = memberName;
        this.password = password;
        this.nickName = nickName;
    }
}
