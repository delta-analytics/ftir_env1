package deltaanalytics.ftir.main.view;

import deltaanalytics.ftir.calibration.view.CalibrationMenuFactory;
import deltaanalytics.ftir.measurement.view.MeasurementMenuFactory;
import deltaanalytics.ftir.usermanagement.view.UserManagementMenuFactory;
import javafx.scene.control.MenuBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuBarFactory {
    @Autowired
    CalibrationMenuFactory calibrationMenuFactory;

    @Autowired
    FileMenuFactory fileMenuFactory;

    @Autowired
    MeasurementMenuFactory measurementMenuFactory;

    @Autowired
    UserManagementMenuFactory userManagementMenuFactory;

    @Autowired
    HelpMenuFactory helpMenuFactory;

    @Autowired
    StatisticMenuFactory statisticMenuFactory;

    public MenuBar build() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(
                fileMenuFactory.build(),
                calibrationMenuFactory.build(),
                measurementMenuFactory.build(),
                statisticMenuFactory.build(),
                userManagementMenuFactory.build(),
                helpMenuFactory.build());
        return menuBar;
    }
}
