package bird.JavaBird.domain;

import lombok.Data;

@Data
public class Display {
    private Long postId;
    private Long memberId;
    private ImageFile uploadFile;
    private String contents;
    private String name;
    private boolean follow;
}
