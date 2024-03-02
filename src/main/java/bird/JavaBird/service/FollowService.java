package bird.JavaBird.service;

import bird.JavaBird.domain.Member;
import bird.JavaBird.repository.FollowRepository;
import bird.JavaBird.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
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
