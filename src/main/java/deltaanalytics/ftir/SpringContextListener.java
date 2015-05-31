package deltaanalytics.ftir;


import deltaanalytics.ftir.usermanagement.controller.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserService userService;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        userService.saveSuperuser("superuser", "superuser");
    }
}
