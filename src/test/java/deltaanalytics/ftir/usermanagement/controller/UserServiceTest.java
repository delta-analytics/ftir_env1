package deltaanalytics.ftir.usermanagement.controller;

import deltaanalytics.ftir.Main;
import deltaanalytics.ftir.usermanagement.model.User;
import deltaanalytics.ftir.usermanagement.model.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    public void saveSuperuserOnce() {
        User superuser = new User();
        superuser.setAccount("superuser");
        superuser.setPassword("superuser");

        userService.saveSuperuser(superuser.getAccount(), superuser.getPassword());
        userService.saveSuperuser(superuser.getAccount(), superuser.getPassword());

        assertThat(userRepository.count(), is(equalTo(1L)));

    }
}
