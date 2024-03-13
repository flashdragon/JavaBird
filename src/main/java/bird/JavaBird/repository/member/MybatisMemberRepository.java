package bird.JavaBird.repository.member;

import bird.JavaBird.domain.Member;
import bird.JavaBird.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        memberMapper.save(member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public Optional<Member> findByMemberName(String memberName) {
        return memberMapper.findByMemberName(memberName);
    }

    @Override
    public void delete(Long id) {
        memberMapper.delete(id);
    }
}
