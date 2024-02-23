package bird.JavaBird.service;

import bird.JavaBird.domain.Member;
import bird.JavaBird.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository repository;

    public Member login(String memberName, String password) {
        return repository.findByMemberName(memberName).filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}
