package deltaanalytics.ftir.usermanagement.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class UserManagementMenuFactory {
    public static Menu build(Stage primaryStage) {
        Menu userManagementMenu = new Menu("Benutzerverwaltung");
        MenuItem menuItemUserOverview = new MenuItem("Übersicht");
        MenuItem menuItemUserCreate = new MenuItem("Nutzer anlegen");
        userManagementMenu.getItems().addAll(menuItemUserOverview, menuItemUserCreate);
        userManagementMenu.setDisable(true);
        return userManagementMenu;
    }
}
