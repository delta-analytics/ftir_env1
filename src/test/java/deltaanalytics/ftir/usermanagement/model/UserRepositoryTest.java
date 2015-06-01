package deltaanalytics.ftir.usermanagement.model;

import deltaanalytics.ftir.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() {
        User user = new User();
        user.setAccount("mlach");
        user.setPassword("mpass");

        userRepository.save(user);

        User savedUser = userRepository.findOne(user.getId());
        assertThat(savedUser, is(equalTo(user)));
    }

    @Test
    public void saveTwoUserAndSuperuserExists() {
        User user1 = new User();
        user1.setAccount("mlach");
        user1.setPassword("mpass");

        userRepository.save(user1);

        User user2 = new User();
        user2.setAccount("mdetz");
        user2.setPassword("mdetz");

        userRepository.save(user2);

        assertThat(userRepository.count(), is(equalTo(3L)));
    }


    @Test
    public void findByAccountAndPassword() {
        User user = new User();
        user.setAccount("mlach");
        user.setPassword("mpass");

        userRepository.save(user);

        User user1 = userRepository.findByAccountAndPassword("mlach", "mpass");
        assertThat(user1, is(equalTo(user)));
        assertThat(user1.getCreatedAt(), is(notNullValue()));
    }

    @Test
    public void findByAccountAndPasswordNoResult() {
        User user = new User();
        user.setAccount("mlach");
        user.setPassword("mpass");

        userRepository.save(user);

        assertThat(userRepository.findByAccountAndPassword("dummy", "dummy"), is(nullValue()));
    }

}
