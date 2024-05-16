package bird.JavaBird.controller;

import bird.JavaBird.SessionConst;
import bird.JavaBird.aop.Retry;
import bird.JavaBird.domain.Member;
import bird.JavaBird.domain.Post;
import bird.JavaBird.dto.PostDto;
import bird.JavaBird.file.FileStore;
import bird.JavaBird.file.LocalFileStore;
import bird.JavaBird.service.PostService;
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
    private final PostService postService;
    private final FileStore fileStore;
    @GetMapping("/post")
    public String post() {
        log.info("post controller");
        return "upload-form";
    }

    @Retry
    @PostMapping("/post")
    public String uploadPost(@Valid @ModelAttribute PostDto form, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        log.info("form={}", form);
        if (bindingResult.hasErrors()) {
            return "upload-form";
        }

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Post post = new Post();
        post.setMemberId(loginMember.getId());
        post.setContents(form.getContents());
        postService.save(post, form.getImageFile());

        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/images/{imageName}")
    public Resource downloadImage(@PathVariable("imageName") String imageName) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(imageName));
    }

    @PostMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId, HttpServletRequest request) throws IOException {
        log.info("deletePost");

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        postService.delete(postId, loginMember.getId());

        return "redirect:/";
    }

}
