package bird.JavaBird.repository;

import bird.JavaBird.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
@Slf4j
public class MemberRepository {
    private final JdbcTemplate template;

    public MemberRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public Member save(Member member) {
        String sql = "insert into member(name, password, nickname) values (?, ?, ?)";
        template.update(sql, member.getMemberName(), member.getPassword(), member.getNickName());
        return member;
    }

    public Member findById(Long id) {
        String sql = "select * from member where id = ?";
        return template.queryForObject(sql, memberRowMapper(), id);
    }

    public Optional<Member> findByMemberName(String memberName) {
        String sql = "select * from member where name = ?";
        try {
            return Optional.ofNullable(template.queryForObject(sql, memberRowMapper(), memberName));
        } catch (EmptyResultDataAccessException e) {
            log.info("error={}", e);
            return Optional.empty();
        }
    }

    public void delete(Long id) {
        String sql = "delete from member where id = ?";
        template.update(sql, id);
    }


    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setMemberName(rs.getString("name"));
            member.setPassword(rs.getString("password"));
            member.setNickName(rs.getString("nickname"));
            return member;
        };
    }

}
