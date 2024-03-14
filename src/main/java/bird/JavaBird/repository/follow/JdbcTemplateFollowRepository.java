package bird.JavaBird.repository.follow;

import bird.JavaBird.repository.FollowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

//@Repository
@Slf4j
@Transactional
public class JdbcTemplateFollowRepository implements FollowRepository {

    private final JdbcTemplate template;

    public JdbcTemplateFollowRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }
    @Override
    public void following(Long member1Id, Long member2Id) {
        String sql = "insert into follow(following, follower) values (?, ?)";
        template.update(sql, member1Id, member2Id);
    }
    @Override
    public void unfollowing(Long member1Id, Long member2Id) {
        String sql = "delete from follow where following = ? and follower = ?";
        template.update(sql, member1Id, member2Id);
    }
    @Override
    public Integer isFollow(Long member1Id, Long member2Id) {
        String sql = "select count(*) from follow where following = ? and follower = ?";
        return template.queryForObject(sql, Integer.class, member1Id, member2Id);
    }
}
