package bird.JavaBird.repository;

import bird.JavaBird.domain.Post;

import java.util.List;

public interface PostRepository {
    Post save(Post post);
    List<Post> findAll();
    void delete(Long postId, Long memberId);
}
