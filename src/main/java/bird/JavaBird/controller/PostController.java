package bird.JavaBird.controller;

import bird.JavaBird.SessionConst;
import bird.JavaBird.domain.ImageFile;
import bird.JavaBird.domain.Member;
import bird.JavaBird.domain.Post;
import bird.JavaBird.file.FileStore;
import bird.JavaBird.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final FileStore fileStore;
    @GetMapping("/post")
    public String post() {
        log.info("post controller");
        return "upload-form";
    }

    @PostMapping("/post")
    public String uploadPost(@Valid @ModelAttribute PostForm form, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        log.info("form={}", form);
        ImageFile imageFile = fileStore.storeFile(form.getUploadFile());

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Post post = new Post();
        post.setMemberId(loginMember.getId());
        post.setContents(form.getContents());
        post.setUploadFile(imageFile);
        postRepository.save(post);

        return "redirect:/";
    }
}
