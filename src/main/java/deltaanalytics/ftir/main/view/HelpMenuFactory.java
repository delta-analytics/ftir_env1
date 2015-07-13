package deltaanalytics.ftir.main.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class HelpMenuFactory {
    public Menu build(Stage primaryStage) {
        Menu helpMenu = new Menu("Hilfe");
        MenuItem menuItemHelp = new MenuItem("Dr. Delta fragen..");
        MenuItem menuItemGlossar = new MenuItem("Glossar");
        helpMenu.getItems().addAll(menuItemHelp, menuItemGlossar);
        return helpMenu;
    }
}
