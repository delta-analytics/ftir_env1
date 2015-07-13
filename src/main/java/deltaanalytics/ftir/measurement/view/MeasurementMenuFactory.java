package deltaanalytics.ftir.measurement.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class MeasurementMenuFactory {
    public static Menu build(Stage primaryStage) {
        Menu measurementMenu = new Menu("Messen");
        MenuItem menuItemMeasurementOverview = new MenuItem("Übersicht");
        MenuItem menuItemMeasurementStart = new MenuItem("Starten");
        measurementMenu.getItems().addAll(menuItemMeasurementOverview, menuItemMeasurementStart);
        return measurementMenu;
    }
}
