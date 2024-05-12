package bird.JavaBird.file;

import bird.JavaBird.domain.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStore {

    String getFullPath(String filename);
    ImageFile storeFile(MultipartFile multipartFile) throws IOException;
}
