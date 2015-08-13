package deltaanalytics.ftir.usermanagement.controller;

import deltaanalytics.ftir.usermanagement.model.User;
import deltaanalytics.ftir.usermanagement.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public void saveSuperuser(String account, String password) {
        if (userRepository.findByAccountAndPassword(account, password) == null) {
            User superuser = new User();
            superuser.setAccount(account);
            superuser.setPassword(password);
            userRepository.save(superuser);
            LOGGER.info("Superuser saved");
        } else {
            LOGGER.warn("Superuser allready existing");
        }
    }

    public User readUser(String account, String password) {
        return userRepository.findByAccountAndPassword(account, password);
    }
}
