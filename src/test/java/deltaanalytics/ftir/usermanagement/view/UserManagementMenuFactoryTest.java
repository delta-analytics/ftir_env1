package deltaanalytics.ftir.usermanagement.view;

import javafx.scene.control.Menu;
import javafx.stage.Stage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class UserManagementMenuFactoryTest {
    @Test
    public void buildUserManagementMenuInCorrectOrder() {
        Menu menu = UserManagementMenuFactory.build(mock(Stage.class));

        assertThat(menu.getText(), is(equalTo("Benutzerverwaltung")));
        assertThat(menu.getItems().get(0).getText(), is(equalTo("Übersicht")));
        assertThat(menu.getItems().get(1).getText(), is(equalTo("Nutzer anlegen")));
    }

}
