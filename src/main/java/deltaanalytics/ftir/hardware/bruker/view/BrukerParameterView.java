package deltaanalytics.ftir.hardware.bruker.view;

import deltaanalytics.ftir.hardware.bruker.controller.BrukerConfigurationService;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameter;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameterOption;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BrukerParameterView {

    @Autowired
    private BrukerConfigurationService brukerConfigurationService;

    public void show(Stage primaryStage) {
        List<BrukerConfigurationParameter> brukerConfigurationParameterList = brukerConfigurationService.readAll();
        Stage stage = new Stage();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Bruker Parameter");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        int y = 1;
        for (BrukerConfigurationParameter brukerConfigurationParameter : brukerConfigurationParameterList) {
            Label paramKey = new Label(brukerConfigurationParameter.getKey());
            grid.add(paramKey, 0, y);

            Label paramDesc = new Label(brukerConfigurationParameter.getDescription());
            grid.add(paramDesc, 1, y);


            List<String> options = new ArrayList<>();
            ComboBox<String> comboBox = new ComboBox<>();
            for (BrukerConfigurationParameterOption brukerConfigurationParameterOption : brukerConfigurationParameter.getBrukerConfigurationParameterOptions()) {
                options.add(brukerConfigurationParameterOption.getValue());
            }
            if (!options.isEmpty()) {
                comboBox.getItems().addAll(options);
                grid.add(comboBox, 2, y);
            } else {
                TextField paramValue = new TextField();
                grid.add(paramValue, 2, y);
            }
            y+=1;
        }

        ScrollPane scrollPane = new ScrollPane(grid);
        Scene scene = new Scene(scrollPane, 800, 600);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        stage.setScene(scene);
        stage.show();
    }
}