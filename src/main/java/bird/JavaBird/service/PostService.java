package bird.JavaBird.service;

import bird.JavaBird.domain.Post;
import bird.JavaBird.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post save(Post post) {
        postRepository.save(post);
        return post;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void delete(Long postId, Long memberId) {
        postRepository.delete(postId, memberId);
    }
}
