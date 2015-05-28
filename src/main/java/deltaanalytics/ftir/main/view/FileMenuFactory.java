package deltaanalytics.ftir.main.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class FileMenuFactory {
    public static Menu build() {
        Menu fileMenu = new Menu("Datei");
        MenuItem menuItemSettings = new MenuItem("Einstellungen");
        MenuItem menuItemInfo = new MenuItem("Info");
        MenuItem menuItemSystemState = new MenuItem("Systemstatus");
        MenuItem menuItemBeenden = new MenuItem("Beenden");
        menuItemBeenden.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        fileMenu.getItems().addAll(menuItemSettings, menuItemInfo, menuItemSystemState, menuItemBeenden);
        return fileMenu;
    }
}
