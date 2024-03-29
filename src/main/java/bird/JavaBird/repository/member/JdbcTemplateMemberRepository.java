package bird.JavaBird.repository.member;

import bird.JavaBird.domain.Member;
import bird.JavaBird.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Optional;

//@Repository
@Slf4j
@Transactional
public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate template;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name, password, nickname) values (?, ?, ?)";
        template.update(sql, member.getMemberName(), member.getPassword(), member.getNickName());
        return member;
    }
    @Override
    public Member findById(Long id) {
        String sql = "select * from member where id = ?";
        return template.queryForObject(sql, memberRowMapper(), id);
    }
    @Override
    public Optional<Member> findByMemberName(String memberName) {
        String sql = "select * from member where name = ?";
        try {
            return Optional.ofNullable(template.queryForObject(sql, memberRowMapper(), memberName));
        } catch (EmptyResultDataAccessException e) {
            log.info("error={}", e);
            return Optional.empty();
        }
    }
    @Override
    public void delete(Long id) {
        String sql = "delete from member where id = ?";
        template.update(sql, id);
    }


    private RowMapper<Member> memberRowMapper() {
        return BeanPropertyRowMapper.newInstance(Member.class);
    }

}
