package bird.JavaBird.domain;

import lombok.Data;

@Data
public class ImageFile {

    private String imageName;
    private String storedName;

    public ImageFile(String imageName, String storedName) {
        this.imageName = imageName;
        this.storedName = storedName;
    }
}
