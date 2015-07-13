package deltaanalytics.ftir.calibration.view;

import deltaanalytics.ftir.FtirResourceBundle;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalibrationMenuFactory {
    @Autowired
    FtirResourceBundle ftirResourceBundle;

    public Menu build(Stage primaryStage) {
        Menu calibrationMenu = new Menu(ftirResourceBundle.getResourceBundle().getString("calibration"));
        MenuItem menuItemCalibrationOverview = new MenuItem("Übersicht");
        MenuItem menuItemCalibrationStart = new MenuItem("Starten");
        calibrationMenu.getItems().addAll(menuItemCalibrationOverview, menuItemCalibrationStart);
        return calibrationMenu;
    }

}
