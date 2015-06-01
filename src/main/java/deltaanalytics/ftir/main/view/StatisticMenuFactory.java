package deltaanalytics.ftir.main.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class StatisticMenuFactory {
    public static Menu build() {
        Menu statisticMenu = new Menu("Statistik");
        MenuItem menuItemStatisticOverview = new MenuItem("Übersicht");
        statisticMenu.getItems().addAll(menuItemStatisticOverview);
        return statisticMenu;
    }
}
