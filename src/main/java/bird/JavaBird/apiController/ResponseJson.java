package bird.JavaBird.apiController;

import bird.JavaBird.domain.Member;
import lombok.Data;

@Data
public class ResponseJson {
    public int code;
    public String message;
    public Member member;
}
