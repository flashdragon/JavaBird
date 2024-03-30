package bird.JavaBird.service;

import bird.JavaBird.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    void save() {
        Member member = new Member("김용재","123","123");
        memberService.save(member);
        Member findMember = memberService.findById(1L);
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findById() {
        Member member1 = new Member("김용재","123","123");
        memberService.save(member1);
        Member member2 = new Member("김이박","333","333");
        memberService.save(member2);
        Member findMember1 = memberService.findById(1L);
        Member findMember2 = memberService.findById(2L);
        assertThat(member1.getMemberName()).isEqualTo(findMember1.getMemberName());
        assertThat(member2.getMemberName()).isEqualTo(findMember2.getMemberName());
    }

    @Test
    void login() {
        Member member = new Member("김용재","123","123");
        memberService.save(member);
        Member loginMember = memberService.login("김용재", "123");
        assertThat(member).isEqualTo(loginMember);
    }
}
