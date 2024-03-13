package bird.JavaBird.repository.member;

import bird.JavaBird.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findById(Long id);

    Optional<Member> findByMemberName(String memberName);

    void delete(Long id);
}
