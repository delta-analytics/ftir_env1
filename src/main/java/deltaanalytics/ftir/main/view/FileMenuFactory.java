package deltaanalytics.ftir.main.view;

import deltaanalytics.ftir.hardware.bruker.view.BrukerParameterView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileMenuFactory {
    @Autowired
    private BrukerParameterView brukerParameterView;

    public Menu build(Stage primaryStage) {
        Menu fileMenu = new Menu("Datei");
        MenuItem menuItemSettings = new MenuItem("Einstellungen");
        MenuItem menuItemBrukerParameter = new MenuItem("Bruker Parameter");
        menuItemBrukerParameter.setOnAction(t -> brukerParameterView.show(primaryStage));
        MenuItem menuItemInfo = new MenuItem("Info");
        MenuItem menuItemSystemState = new MenuItem("Systemstatus");
        MenuItem menuItemBeenden = new MenuItem("Beenden");
        menuItemBeenden.setOnAction(t -> System.exit(0));
        fileMenu.getItems().addAll(menuItemSettings, menuItemBrukerParameter, menuItemInfo, menuItemSystemState, menuItemBeenden);
        return fileMenu;
    }
}
