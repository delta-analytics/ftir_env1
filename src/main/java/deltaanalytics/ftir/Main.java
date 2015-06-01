package deltaanalytics.ftir;

import deltaanalytics.ftir.login.view.LoginFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {

    @Autowired
    private LoginFactory loginFactory;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        loginFactory.build(primaryStage);
    }

    public static void main(String[] args) {
        launchApp(Main.class, args);
    }
}