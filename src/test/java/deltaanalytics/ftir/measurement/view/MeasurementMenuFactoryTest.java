package deltaanalytics.ftir.measurement.view;

import javafx.scene.control.Menu;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MeasurementMenuFactoryTest {
    @Test
    public void buildMeasurementMenuInCorrectOrder() {
        Menu menu = MeasurementMenuFactory.build();

        assertThat(menu.getText(), is(equalTo("Messen")));
        assertThat(menu.getItems().get(0).getText(), is(equalTo("Übersicht")));
        assertThat(menu.getItems().get(1).getText(), is(equalTo("Starten")));
    }

}
