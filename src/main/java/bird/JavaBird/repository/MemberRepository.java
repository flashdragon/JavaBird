package bird.JavaBird.repository;

import bird.JavaBird.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);
    Optional<Member> findByMemberName(String memberName);
    void delete(Long id);
}
