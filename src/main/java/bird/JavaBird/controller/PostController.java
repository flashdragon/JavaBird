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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;

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

    @ResponseBody
    @GetMapping("/images/{imageName}")
    public Resource downloadImage(@PathVariable String imageName) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(imageName));
    }

    @PostMapping("/post/{postId}/delete")
    public String deletePost(@PathVariable Long postId, HttpServletRequest request) throws IOException {
        log.info("deletePost");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        postRepository.delete(postId, loginMember.getId());

        return "redirect:/";
    }

}
