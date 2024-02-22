package bird.JavaBird.repository;

import bird.JavaBird.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MemberRepository {
    private final JdbcTemplate template;
    private int id = 1;

    public MemberRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public Member save(Member member) {
        String sql = "insert into member(id, name, password, nickname) values (?, ?, ?, ?)";
        template.update(sql, id, member.getMemberName(), member.getPassword(), member.getNickName());
        id++;
        return member;
    }

    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";
        return template.queryForObject(sql, memberRowMapper(), memberId);
    }


    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberName(rs.getString("name"));
            member.setPassword(rs.getString("password"));
            member.setNickName(rs.getString("nickname"));
            return member;
        };
    }

}
