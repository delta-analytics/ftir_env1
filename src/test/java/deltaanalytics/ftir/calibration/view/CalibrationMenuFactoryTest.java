package deltaanalytics.ftir.calibration.view;

import deltaanalytics.ftir.Main;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class CalibrationMenuFactoryTest {

    @Autowired
    CalibrationMenuFactory calibrationMenuFactory;

    @Test
    public void buildCalibrationMenuInCorrectOrder() {
        Menu menu = calibrationMenuFactory.build(mock(Stage.class));

        assertThat(menu.getText(), is(equalTo("Calibration")));
        assertThat(menu.getItems().get(0).getText(), is(equalTo("Übersicht")));
        assertThat(menu.getItems().get(1).getText(), is(equalTo("Starten")));
    }

}
