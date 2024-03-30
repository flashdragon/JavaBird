package bird.JavaBird.service;

import bird.JavaBird.domain.ImageFile;
import bird.JavaBird.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    PostService postService;


    @Test
    void save() {
        Post post = new Post();
        post.setMemberId(1L);
        post.setContents("안녕하세요");
        ImageFile imageFile = new ImageFile("123","123123");
        post.setImageFile(imageFile);
        Post savePost = postService.save(post);
        assertThat(post).isEqualTo(savePost);
    }

    @Test
    void findAll() {
        Post post1 = new Post();
        post1.setMemberId(1L);
        post1.setContents("안녕하세요");
        ImageFile imageFile1 = new ImageFile("123","123123");
        post1.setImageFile(imageFile1);
        postService.save(post1);
        Post post2 = new Post();
        post2.setMemberId(2L);
        post2.setContents("그러세요");
        ImageFile imageFile2 = new ImageFile("222","222222");
        post2.setImageFile(imageFile2);
        postService.save(post2);
        assertThat(postService.findAll().size()).isEqualTo(2);
    }

    @Test
    void delete() {
        Post post = new Post();
        post.setMemberId(1L);
        post.setContents("안녕하세요");
        ImageFile imageFile = new ImageFile("123","123123");
        post.setImageFile(imageFile);
        postService.save(post);
        assertThat(postService.findAll().size()).isEqualTo(1);
        postService.delete(1L,1L);
        assertThat(postService.findAll().size()).isEqualTo(0);
    }
}
