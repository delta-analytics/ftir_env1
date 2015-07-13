package deltaanalytics.ftir.main.view;

import javafx.scene.control.Menu;
import javafx.stage.Stage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class StatisticMenuFactoryTest {
    @Test
    public void buildStatisticMenuInCorrectOrder() {
        Menu menu = new StatisticMenuFactory().build(mock(Stage.class));

        assertThat(menu.getText(), is(equalTo("Statistik")));
        assertThat(menu.getItems().get(0).getText(), is(equalTo("Übersicht")));
    }

}
