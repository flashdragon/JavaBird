package bird.JavaBird.domain;

import lombok.Data;

@Data
public class Post {

    private Long postId;
    private Long memberId;
    private ImageFile uploadFile;
    private String contents;
}
