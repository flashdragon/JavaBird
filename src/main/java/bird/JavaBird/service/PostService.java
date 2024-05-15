package bird.JavaBird.service;

import bird.JavaBird.domain.ImageFile;
import bird.JavaBird.domain.Post;
import bird.JavaBird.file.FileStore;
import bird.JavaBird.file.LocalFileStore;
import bird.JavaBird.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final FileStore fileStore;

    public Post save(Post post, MultipartFile file) throws IOException {
        log.info("post service={}", post);
        if(!file.isEmpty()) {
            ImageFile imageFile = fileStore.storeFile(file);
            log.info("post stored");
            post.setImageFile(imageFile);
        }
        postRepository.save(post);
        return post;
    }

    public List<Post> findAll(int page) {
        return postRepository.findAll(page);
    }

    public boolean delete(Long postId, Long memberId) {
        postRepository.delete(postId, memberId);
        return true;
    }
}
