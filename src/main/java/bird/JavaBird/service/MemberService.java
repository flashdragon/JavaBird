package bird.JavaBird.service;

import bird.JavaBird.domain.Member;
import bird.JavaBird.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(Member member) {
        memberRepository.save(member);
        return member;
    }
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

}
