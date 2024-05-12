package bird.JavaBird.file;

import bird.JavaBird.domain.ImageFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3FileStore implements FileStore{

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Override
    public String getFullPath(String filename) {
        return null;
    }

    @Override
    public ImageFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/"+extractExt(storeFileName));
        PutObjectResult putObjectResult = amazonS3Client.putObject(new PutObjectRequest(bucket, storeFileName, multipartFile.getInputStream(), metadata)
                                                            .withCannedAcl(CannedAccessControlList.PublicRead));

        return new ImageFile(originalFilename, amazonS3Client.getUrl(bucket, storeFileName).toString());
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
