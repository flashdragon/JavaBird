package bird.JavaBird.repository.follow;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowMapper {

    void following(@Param("member1Id") Long member1Id, @Param("member2Id") Long member2Id);

    void unfollowing(@Param("member1Id") Long member1Id, @Param("member2Id") Long member2Id);

    Integer isFollow(@Param("member1Id") Long member1Id, @Param("member2Id") Long member2Id);
}
