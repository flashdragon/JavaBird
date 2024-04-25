package bird.JavaBird.apiController;

import bird.JavaBird.SessionConst;
import bird.JavaBird.dto.PostDto;
import bird.JavaBird.domain.ImageFile;
import bird.JavaBird.domain.Member;
import bird.JavaBird.domain.Post;
import bird.JavaBird.exception.PostException;
import bird.JavaBird.file.FileStore;
import bird.JavaBird.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final FileStore fileStore;

    @PostMapping("/post")
    public ApiResult<Post> uploadPost(@Valid @ModelAttribute PostDto form, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        log.info("form={}", form);
        if (bindingResult.hasErrors()) {
            throw new PostException("잘못된 형식입니다.");
        }
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Post post = new Post();
        post.setMemberId(loginMember.getId());
        post.setContents(form.getContents());
        return success(postService.save(post, form.getImageFile()));
    }


    @DeleteMapping("/post/{postId}")
    public ApiResult<Boolean> deletePost(@PathVariable("postId") Long postId, HttpServletRequest request) throws IOException {
        log.info("deletePost");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return success(postService.delete(postId, loginMember.getId()));
    }

}
