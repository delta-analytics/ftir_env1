package deltaanalytics.ftir;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class FtirResourceBundle {
    private ResourceBundle resourceBundle;

    @PostConstruct
    public void init() {
        resourceBundle = ResourceBundle.getBundle("messages", Locale.GERMAN);
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

}
