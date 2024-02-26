package bird.JavaBird.controller;

import bird.JavaBird.domain.ImageFile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {
    @GetMapping("/post")
    public String post() {
        return "upload-form";
    }

    @PostMapping("/post")
    public String uploadPost(@Valid @ModelAttribute PostForm form, BindingResult bindingResult) {
        return "";
    }
}
