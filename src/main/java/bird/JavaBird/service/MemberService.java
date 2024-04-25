package bird.JavaBird.service;

import bird.JavaBird.domain.Member;
import bird.JavaBird.exception.LoginException;
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

    public Member login(String memberName, String password) {
        return memberRepository.findByMemberName(memberName).filter(m -> m.getPassword().equals(password)).orElseThrow(() -> new LoginException("잘못된 아이디 또는 비밀번호입니다."));
    }

}
