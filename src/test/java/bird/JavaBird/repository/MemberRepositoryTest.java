package bird.JavaBird.repository;

import bird.JavaBird.domain.Member;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import java.util.Optional;

import static bird.JavaBird.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;


@Slf4j
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository repository;

    @TestConfiguration
    static class TestConfig {
        private final DataSource dataSource;
        public TestConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }
        @Bean
        MemberRepository memberRepository() {
            return new MemberRepository(dataSource);
        }

    }

    @BeforeEach
    void beforeEach() {
    }

    @AfterEach
    void afterEach() {

    }
    @Test
    void save() {
        Member member = new Member();
        member.setMemberName("김용재");
        member.setPassword("1234");
        member.setNickName("qwer");
        repository.save(member);
        Member findMember = repository.findByMemberName("김용재").orElse(null);
        assertThat(findMember.getMemberName()).isEqualTo(member.getMemberName());
        log.info("member={}",member);
        log.info("findmember={}",findMember);
        log.info("id={}",findMember.getId());
        repository.delete(findMember.getId());
    }
    @Test
    void findById() {
        Member member = repository.findById(1L);
        assertThat(member.getMemberName()).isEqualTo("yooh3574");
    }
    @Test
    void findByName() {
       log.info("hi, {}", repository.findByMemberName("111").orElse(null));
    }
}
