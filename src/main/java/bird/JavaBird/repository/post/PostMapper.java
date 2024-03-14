package bird.JavaBird.repository.post;

import bird.JavaBird.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PostMapper {

    void save(Post post);

    List<Post> findAll();

    void delete(@Param("postId")Long postId, @Param("memberId") Long memberId);
}
