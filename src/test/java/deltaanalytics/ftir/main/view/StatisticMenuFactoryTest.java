package deltaanalytics.ftir.main.view;

import javafx.scene.control.Menu;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatisticMenuFactoryTest {
    @Test
    public void buildStatisticMenuInCorrectOrder() {
        Menu menu = StatisticMenuFactory.build();

        assertThat(menu.getText(), is(equalTo("Statistik")));
        assertThat(menu.getItems().get(0).getText(), is(equalTo("Übersicht")));
    }

}
