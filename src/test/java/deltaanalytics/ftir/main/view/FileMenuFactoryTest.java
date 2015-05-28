package deltaanalytics.ftir.main.view;

import javafx.scene.control.Menu;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileMenuFactoryTest {
    @Test
    public void buildFileMenuInCorrectOrder() {
        Menu menu = FileMenuFactory.build();

        assertThat(menu.getText(), is(equalTo("Datei")));
        assertThat(menu.getItems().get(0).getText(), is(equalTo("Einstellungen")));
        assertThat(menu.getItems().get(1).getText(), is(equalTo("Info")));
        assertThat(menu.getItems().get(2).getText(), is(equalTo("Systemstatus")));
        assertThat(menu.getItems().get(3).getText(), is(equalTo("Beenden")));
    }

}
