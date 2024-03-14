package bird.JavaBird.service;

import bird.JavaBird.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {
    private final FollowRepository followRepository;

    public void following(Long member1Id, Long member2Id) {
        followRepository.following(member1Id, member2Id);
    }

    public void unfollowing(Long member1Id, Long member2Id) {
        followRepository.unfollowing(member1Id, member2Id);
    }

    public boolean isFollow(Long member1Id, Long member2Id) {
        if(followRepository.isFollow(member1Id, member2Id) == 1)
            return true;
        else return false;
    }
}
