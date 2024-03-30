package bird.JavaBird.apiController;

import bird.JavaBird.SessionConst;
import bird.JavaBird.controller.PostForm;
import bird.JavaBird.domain.ImageFile;
import bird.JavaBird.domain.Member;
import bird.JavaBird.domain.Post;
import bird.JavaBird.exception.LoginException;
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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiPostController {
    private final PostService postService;
    private final FileStore fileStore;

    @PostMapping("/post")
    public ResponseJson uploadPost(@Valid @ModelAttribute PostForm form, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        log.info("form={}", form);
        if (bindingResult.hasErrors()) {
            throw new LoginException("잘못된 형식입니다.");
        }
        ImageFile imageFile = fileStore.storeFile(form.getImageFile());

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Post post = new Post();
        post.setMemberId(loginMember.getId());
        post.setContents(form.getContents());
        post.setImageFile(imageFile);
        postService.save(post);
        ResponseJson responseJson = new ResponseJson();
        responseJson.setCode(200);
        responseJson.setMessage("success");
        return responseJson;
    }


    @DeleteMapping("/post/{postId}")
    public ResponseJson deletePost(@PathVariable("postId") Long postId, HttpServletRequest request) throws IOException {
        log.info("deletePost");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        postService.delete(postId, loginMember.getId());
        ResponseJson responseJson = new ResponseJson();
        responseJson.setCode(200);
        responseJson.setMessage("success");
        return responseJson;
    }

}
