package deltaanalytics.ftir;

import deltaanalytics.ftir.main.view.MenuBarFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {

    @Autowired
    private FtirResourceBundle ftirResourceBundle;

    @Autowired
    private MenuBarFactory menuBarFactory;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        primaryStage.setTitle(ftirResourceBundle.getResourceBundle().getString("main.title"));
        MenuBar menuBar = menuBarFactory.build();
        root.setTop(menuBar);
        primaryStage.setScene(new Scene(root));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launchApp(Main.class, args);
    }
}