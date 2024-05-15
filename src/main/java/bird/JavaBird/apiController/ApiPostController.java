package bird.JavaBird.apiController;

import bird.JavaBird.dto.LoginInfoDto;
import bird.JavaBird.dto.PostDto;
import bird.JavaBird.domain.Post;
import bird.JavaBird.exception.PostException;
import bird.JavaBird.security.login.Login;
import bird.JavaBird.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static bird.JavaBird.utils.ApiUtils.*;
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiPostController {
    private final PostService postService;

    @PostMapping("/post")
    public ApiResult<String> uploadPost(@Valid @ModelAttribute PostDto form, BindingResult bindingResult, @Login LoginInfoDto loginMember) throws IOException {
        log.info("form ={}", form);
        if (bindingResult.hasErrors()) {
            throw new PostException("잘못된 형식입니다.");
        }

        Post post = new Post();
        post.setMemberId(loginMember.getMemberId());
        post.setContents(form.getContents());
        postService.save(post, form.getImageFile());
        return success("ok");
    }


    @DeleteMapping("/post/{postId}")
    public ApiResult<Boolean> deletePost(@PathVariable("postId") Long postId, @Login LoginInfoDto loginMember) throws IOException {
        log.info("deletePost");

        return success(postService.delete(postId, loginMember.getMemberId()));
    }

}
