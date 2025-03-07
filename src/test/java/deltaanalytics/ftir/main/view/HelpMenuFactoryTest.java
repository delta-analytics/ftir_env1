package deltaanalytics.ftir.main.view;

import javafx.scene.control.Menu;
import javafx.stage.Stage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class HelpMenuFactoryTest {
    @Test
    public void buildHelpMenuInCorrectOrder() {
        Menu menu = new HelpMenuFactory().build(mock(Stage.class));

        assertThat(menu.getText(), is(equalTo("Hilfe")));
        assertThat(menu.getItems().get(0).getText(), is(equalTo("Dr. Delta fragen..")));
        assertThat(menu.getItems().get(1).getText(), is(equalTo("Glossar")));
    }

}
