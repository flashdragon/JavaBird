package bird.JavaBird.service;

import bird.JavaBird.domain.ImageFile;
import bird.JavaBird.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    PostService postService;


//    @Test
//    @DirtiesContext
    void save() throws IOException {
        Post post = new Post();
        post.setMemberId(1L);
        post.setContents("안녕하세요");
        ImageFile imageFile = new ImageFile("123","123123");
        post.setImageFile(imageFile);
        Post savePost = postService.save(post, new MockMultipartFile("filename", "", null, new byte[0]));
        assertThat(post).isEqualTo(savePost);
    }

//    @Test
//    @DirtiesContext
    void findAll() throws IOException {
        Post post1 = new Post();
        post1.setMemberId(1L);
        post1.setContents("안녕하세요");
        ImageFile imageFile1 = new ImageFile("123","123123");
        post1.setImageFile(imageFile1);
        postService.save(post1, new MockMultipartFile("filename", "", null, new byte[0]));
        Post post2 = new Post();
        post2.setMemberId(2L);
        post2.setContents("그러세요");
        ImageFile imageFile2 = new ImageFile("222","222222");
        post2.setImageFile(imageFile2);
        postService.save(post2, new MockMultipartFile("filename", "", null, new byte[0]));
        assertThat(postService.findAll().size()).isEqualTo(2);
    }

 //   @Test
 //   @DirtiesContext
    void delete() throws IOException {
        Post post = new Post();
        post.setMemberId(1L);
        post.setContents("안녕하세요");
        ImageFile imageFile = new ImageFile("123","123123");
        post.setImageFile(imageFile);
        postService.save(post, new MockMultipartFile("filename", "", null, new byte[0]));
        assertThat(postService.findAll().size()).isEqualTo(1);
        postService.delete(1L,1L);
        assertThat(postService.findAll().size()).isEqualTo(0);
    }
}
