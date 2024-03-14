package bird.JavaBird.repository.post;

import bird.JavaBird.domain.Post;
import bird.JavaBird.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class MybatisPostRepository implements PostRepository {
    private final PostMapper postMapper;
    @Override
    public Post save(Post post) {
        postMapper.save(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return postMapper.findAll();
    }

    @Override
    public void delete(Long postId, Long memberId) {
        postMapper.delete(postId, memberId);
    }
}
