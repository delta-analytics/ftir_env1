package deltaanalytics.ftir.login.view;

import deltaanalytics.ftir.FtirResourceBundle;
import deltaanalytics.ftir.main.view.MenuBarFactory;
import deltaanalytics.ftir.usermanagement.controller.UserService;
import java.awt.SplashScreen;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//// used to test Jueke serial connection at Login
//import deltaanalytics.ftir.hardware.jueke.controller.JuekeSerialConnection;
//import java.util.logging.Level;
//import java.util.logging.Logger;

@Component
public class LoginFactory {
    @Autowired
    private FtirResourceBundle ftirResourceBundle;

    @Autowired
    private MenuBarFactory menuBarFactory;

    @Autowired
    private UserService userService;


    public void build(Stage primaryStage) {
 
        // +++++++++++++++++++++++++++++++++++++++++++++
        // Center
        // +++++++++++++++++++++++++++++++++++++++++++++

        // Layout
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false);
        grid.getColumnConstraints().add(new ColumnConstraints(20)); // column 1
        grid.getColumnConstraints().add(new ColumnConstraints(80)); // column 2
        grid.getColumnConstraints().add(new ColumnConstraints(300)); // column 3
        grid.getColumnConstraints().add(new ColumnConstraints(80)); // column 4
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setPadding(new Insets(20));

        ImageView logo = new ImageView();
        logo.setImage(new Image("file:/logo.jpg"));

        Image i_logo = new Image(getClass()
                .getResource("/logo.jpg").toExternalForm(), 100, 100, false, false);

        final ImageView v_logo = new ImageView(i_logo);

        grid.add(v_logo, 4, 0);
        GridPane.setHalignment(v_logo, HPos.RIGHT);
        GridPane.setValignment(v_logo, VPos.TOP);


        // Ueberschrift
        Text logintopTx = new Text("LOGIN");

        logintopTx.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        grid.add(logintopTx, 2, 0);
        GridPane.setHalignment(logintopTx, HPos.CENTER);
        GridPane.setValignment(logintopTx, VPos.TOP);

        // Vorname
        Label vornameLB = new Label("Account:");
        grid.add(vornameLB, 1, 2);
        TextField accountTX = new TextField();
        accountTX.setText("superuser");
        grid.add(accountTX, 2, 2);
        //grid.getRowConstraints().add(0,new RowConstraints(40));

        Label passwordLB = new Label("Passwort:");
        grid.add(passwordLB, 1, 3);
        PasswordField passfield = new PasswordField();
        passfield.setText("superuser");
        passfield.setPromptText("password eingeben");


        grid.add(passfield, 2, 3);
        Label meldungLB = new Label("Meldung:");
        grid.add(meldungLB, 1, 4);
        TextField meldungTX = new TextField();
        grid.add(meldungTX, 2, 4);
        meldungTX.setEditable(false);

        Label spracheLB = new Label("Sprache:");
        grid.add(spracheLB, 3, 2);
        ComboBox<String> cb_sp = new ComboBox<>();
        cb_sp.setEditable(false);
        cb_sp.getItems().addAll("English", "Deutsch");
        cb_sp.setValue(cb_sp.getItems().get(0));
        grid.add(cb_sp, 4, 2);

        Button loginBtn = new Button("LOGIN");
        grid.add(loginBtn, 2, 5);
        GridPane.setHalignment(loginBtn, HPos.CENTER);
        GridPane.setValignment(loginBtn, VPos.TOP);

        CheckBox pw_sichtbar = new CheckBox("Passwort sichtbar gibts nicht");
        pw_sichtbar.setAllowIndeterminate(false);
        pw_sichtbar.setSelected(true);
        //grid.add(pw_sichtbar, 2, 6);

        HBox hbBtn = new HBox(10);  //hier nicht sinnvoll
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);

        hbBtn.getChildren().add(loginBtn);

        grid.add(hbBtn, 2, 5);


        // Szene
        Scene scene = new Scene(grid);

        primaryStage.getIcons().add(new Image(LoginFactory.class.getResourceAsStream("/logo.jpg")));
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("Delta Analytics");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.sizeToScene();
        primaryStage.show();

        loginBtn.setOnAction(event -> {
            meldungTX.setText("Prüfung noch nicht eingebunden!!!");
            if (userService.readUser(accountTX.getText(), passfield.getText()) != null) {
                    BorderPane root = new BorderPane();
                    primaryStage.setTitle(ftirResourceBundle.getResourceBundle().getString("main.title"));
                    MenuBar menuBar = menuBarFactory.build(primaryStage);
                    root.setTop(menuBar);
                    primaryStage.setScene(new Scene(root));
                    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                    //primaryStage.setX(primaryScreenBounds.getMinX());
                    //primaryStage.setY(primaryScreenBounds.getMinY());
                    //primaryStage.setWidth(primaryScreenBounds.getWidth());
                    //primaryStage.setHeight(primaryScreenBounds.getHeight());
//                try {                    
//                    // test Jueke part at Login
//                    // !!!! first setup virtual ports like
//                    // !!!! sudo socat -d -d PTY,link=/dev/ttyS10 PTY,link=/dev/ttyS11
//                    // !!!! sudo chmod 777 /dev/ttyS10 /dev/ttyS11
//                    // !!!! gtkterm using port setting /dev/ttyS10 
//                    // !!!! with 57600 baud rate, using "local echo" and "CR LF Auto"
//                    JuekeSerialConnection connection = new JuekeSerialConnection();
//                    connection.performTests(false);
//                    connection.findPortToConnect();
                    primaryStage.show();
//                } catch (Exception ex) {
//                    Logger.getLogger(LoginFactory.class.getName()).log(Level.SEVERE, null, ex);
//                }
            } else {
                meldungTX.setText("Account oder Passwort nicht korrekt");
                int depth = 70; //Setting the uniform variable for the glow width and height
                DropShadow borderGlow = new DropShadow();
                borderGlow.setOffsetY(0f);
                borderGlow.setOffsetX(0f);
                borderGlow.setColor(Color.RED);
                borderGlow.setWidth(depth);
                borderGlow.setHeight(depth);
                meldungTX.setEffect(borderGlow);
                accountTX.setText("");
                passfield.setText("");
            }
        });
    }
}
