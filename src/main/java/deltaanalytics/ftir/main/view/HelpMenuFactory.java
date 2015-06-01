package deltaanalytics.ftir.main.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class HelpMenuFactory {
    public static Menu build() {
        Menu helpMenu = new Menu("Hilfe");
        MenuItem menuItemHelp = new MenuItem("Dr. Delta fragen..");
        MenuItem menuItemGlossar = new MenuItem("Glossar");
        helpMenu.getItems().addAll(menuItemHelp, menuItemGlossar);
        return helpMenu;
    }
}
