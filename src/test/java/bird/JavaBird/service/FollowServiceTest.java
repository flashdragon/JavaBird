package bird.JavaBird.service;

import bird.JavaBird.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FollowServiceTest {
    @Autowired
    FollowService followService;
    @Autowired
    MemberService memberService;

    @Test
    @DirtiesContext
    void following() {
        Member member1 = new Member("김용재","123","123");
        memberService.save(member1);
        Member member2 = new Member("김이박","333","333");
        memberService.save(member2);
        followService.following(1l,2l);
        assertThat(followService.isFollow(1l,2l)).isEqualTo(true);
    }

    @Test
    @DirtiesContext
    void unfollowing() {
        Member member1 = new Member("김용재","123","123");
        memberService.save(member1);
        Member member2 = new Member("김이박","333","333");
        memberService.save(member2);
        followService.following(1l,2l);
        assertThat(followService.isFollow(1l,2l)).isEqualTo(true);
        followService.unfollowing(1l,2l);
        assertThat(followService.isFollow(1l,2l)).isEqualTo(false);
    }
}
