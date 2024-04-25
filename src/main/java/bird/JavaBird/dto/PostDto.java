package bird.JavaBird.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDto {
    private MultipartFile imageFile;
    @NotEmpty
    private String contents;
}
