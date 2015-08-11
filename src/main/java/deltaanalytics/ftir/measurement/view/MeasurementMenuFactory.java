package deltaanalytics.ftir.measurement.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementMenuFactory {
    private BrukerMeasurementView brukerMeasurementView;

    public Menu build(Stage primaryStage) {
        Menu measurementMenu = new Menu("Messen");
        MenuItem menuItemMeasurementOverview = new MenuItem("Übersicht");
        menuItemMeasurementOverview.setDisable(true);
        MenuItem menuItemMeasurementStart = new MenuItem("Starten");
        menuItemMeasurementStart.setOnAction(t -> brukerMeasurementView.show(primaryStage));
        measurementMenu.getItems().addAll(menuItemMeasurementOverview, menuItemMeasurementStart);
        return measurementMenu;
    }

    @Autowired
    private void setBrukerMeasurementView(BrukerMeasurementView brukerMeasurementView){
        this.brukerMeasurementView = brukerMeasurementView;
    }
}
