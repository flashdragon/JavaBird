package bird.JavaBird.repository;

import bird.JavaBird.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Slf4j
public class FollowRepository {

    private final JdbcTemplate template;

    public FollowRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public void following(Long member1Id, Long member2Id) {
        String sql = "insert into follow(following, follower) values (?, ?)";
        template.update(sql, member1Id, member2Id);
    }

    public void unfollowing(Long member1Id, Long member2Id) {
        String sql = "delete from follow where following = ? and follower = ?";
        template.update(sql, member1Id, member2Id);
    }

    public Integer isFollow(Long member1Id, Long member2Id) {
        String sql = "select count(*) from follow where following = ? and follower = ?";
        return template.queryForObject(sql, Integer.class, member1Id, member2Id);
    }
}
