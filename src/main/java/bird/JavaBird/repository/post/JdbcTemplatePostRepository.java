package bird.JavaBird.repository.post;

import bird.JavaBird.domain.ImageFile;
import bird.JavaBird.domain.Post;
import bird.JavaBird.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class JdbcTemplatePostRepository implements PostRepository {
    private final JdbcTemplate template;
    public JdbcTemplatePostRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Post save(Post post) {
        String sql = "insert into post(memberid, name, storedname, contents) values (?, ?, ?, ?)";
        template.update(sql, post.getMemberId(), post.getUploadFile().getImageName(), post.getUploadFile().getStoredName(), post.getContents());
        return post;
    }
    @Override
    public List<Post> findAll() {
        String sql = "select * from post";
        return template.query(sql , postRowMapper());
    }

    @Override
    public void delete(Long postId, Long memberId) {
        String sql = "delete from post where postid = ? and memberid = ?";
        template.update(sql, postId, memberId);
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setPostId(rs.getLong("postid"));
            post.setMemberId(rs.getLong("memberid"));
            post.setContents(rs.getString("contents"));
            post.setUploadFile(new ImageFile(rs.getString("name"),rs.getString("storedname")));
            return post;
        };
    }

}
