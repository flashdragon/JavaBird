package bird.JavaBird.repository.follow;

import bird.JavaBird.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MybatisFollowRepository implements FollowRepository {

    private final FollowMapper followMapper;

    @Override
    public void following(Long member1Id, Long member2Id) {
        followMapper.following(member1Id, member2Id);
    }

    @Override
    public void unfollowing(Long member1Id, Long member2Id) {
        followMapper.unfollowing(member1Id, member2Id);
    }

    @Override
    public Integer isFollow(Long member1Id, Long member2Id) {
        return followMapper.isFollow(member1Id, member2Id);
    }
}
