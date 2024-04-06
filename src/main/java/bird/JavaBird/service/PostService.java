package bird.JavaBird.service;

import bird.JavaBird.domain.ImageFile;
import bird.JavaBird.domain.Post;
import bird.JavaBird.file.FileStore;
import bird.JavaBird.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final FileStore fileStore;

    public Post save(Post post, MultipartFile file) throws IOException {
        ImageFile imageFile = fileStore.storeFile(file);
        post.setImageFile(imageFile);
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
