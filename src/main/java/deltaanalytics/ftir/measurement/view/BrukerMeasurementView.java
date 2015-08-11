package deltaanalytics.ftir.measurement.view;

import deltaanalytics.ftir.hardware.bruker.controller.BrukerMeasurementService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrukerMeasurementView {

    private BrukerMeasurementService brukerMeasurementService;

    public void show(Stage primaryStage) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(8);
        gridPane.setPadding(new Insets(10));
        Label expLbl = new Label("EXP");
        gridPane.add(expLbl, 0, 0);
        TextField expTx = new TextField("");
        gridPane.add(expTx, 1, 0);
        Label xppLbl = new Label("XPP");
        gridPane.add(xppLbl, 2, 0);
        TextField xppTx = new TextField("");
        gridPane.add(xppTx, 3, 0);
        Button refMeasBtn = new Button("Start Reference Measurement");
        refMeasBtn.setOnAction(t -> brukerMeasurementService.runReferenceMeasurement(expTx.getText(), xppTx.getText()));
        gridPane.add(refMeasBtn, 4, 0);

        Label expLblMeas = new Label("EXP");
        gridPane.add(expLblMeas, 0, 1);
        TextField expTxMeas = new TextField("");
        gridPane.add(expTxMeas, 1, 1);
        Label xppLblMeas = new Label("XPP");
        gridPane.add(xppLblMeas, 2, 1);
        TextField xppTxMeas = new TextField("");
        gridPane.add(xppTxMeas, 3, 1);
        Button measBtn = new Button("Start Measurement");
        measBtn.setMaxWidth(Double.MAX_VALUE);
        measBtn.setOnAction(t -> brukerMeasurementService.runMeasurement(expTxMeas.getText(), xppTxMeas.getText()));
        gridPane.add(measBtn, 4, 1);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Start Measurement");
        stage.show();
    }

    @Autowired
    private void setBrukerMeasurementService(BrukerMeasurementService brukerMeasurementService) {
        this.brukerMeasurementService = brukerMeasurementService;
    }
}
