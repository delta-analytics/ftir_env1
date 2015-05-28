package deltaanalytics.ftir.usermanagement.model;

import deltaanalytics.ftir.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
        assertThat(savedUser.getAccount(), is(equalTo(user.getAccount())));
        assertThat(savedUser.getPassword(), is(equalTo(user.getPassword())));
    }

    @Test
    public void findByAccountAndPassword(){
        User user = new User();
        user.setAccount("mlach");
        user.setPassword("mpass");

        userRepository.save(user);

        assertThat(userRepository.findByAccountAndPassword("mlach", "mpass"), is(equalTo(user)));
    }

    @Test
    public void findByAccountAndPasswordNoResult(){
        User user = new User();
        user.setAccount("mlach");
        user.setPassword("mpass");

        userRepository.save(user);

        assertThat(userRepository.findByAccountAndPassword("dummy", "dummy"), is(nullValue()));
    }
}
