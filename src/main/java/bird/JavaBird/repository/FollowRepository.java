package bird.JavaBird.repository;

public interface FollowRepository {
    void following(Long member1Id, Long member2Id);

    void unfollowing(Long member1Id, Long member2Id);

    Integer isFollow(Long member1Id, Long member2Id);
}
