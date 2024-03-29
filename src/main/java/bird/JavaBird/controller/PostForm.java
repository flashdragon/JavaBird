package bird.JavaBird.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostForm {
    private MultipartFile imageFile;
    @NotEmpty
    private String contents;
}
