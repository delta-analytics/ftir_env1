package deltaanalytics.ftir.hardware.bruker.view;

import deltaanalytics.ftir.hardware.bruker.controller.BrukerConfigurationService;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameter;
import deltaanalytics.ftir.hardware.bruker.model.BrukerConfigurationParameterOption;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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

            TextField paramValue = new TextField();
            grid.add(paramValue, 2, y);

            StringBuilder options = new StringBuilder();
            for (BrukerConfigurationParameterOption brukerConfigurationParameterOption : brukerConfigurationParameter.getBrukerConfigurationParameterOptions()) {
                options.append(brukerConfigurationParameterOption.getValue());
                options.append(", ");
            }
            String optionString = options.toString();
            if(optionString.length()>0){
                optionString = optionString.substring(0, optionString.length()-2);
            }
            Label possibleOptions = new Label(optionString);
            grid.add(possibleOptions, 3, y);
            y += 1;
        }

        ScrollPane scrollPane = new ScrollPane(grid);
        Scene scene = new Scene(scrollPane, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}