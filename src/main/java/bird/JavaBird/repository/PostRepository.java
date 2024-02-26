package bird.JavaBird.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Slf4j
public class PostRepository {
    private final JdbcTemplate template;
    public PostRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

}
