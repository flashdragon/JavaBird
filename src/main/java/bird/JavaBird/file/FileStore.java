package bird.JavaBird.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStore {

    @Value("{file.dir}")
    private String fileDir;
}
