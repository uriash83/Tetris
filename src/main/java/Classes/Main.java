package Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {


    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/PaneWindow.fxml"));
        Pane pane = loader.load();

        Rectangle rect1 = new Rectangle( 25, 25);
        rect1.setFill(Color.BLUE);
        rect1.setLayoutX(0);
        rect1.setLayoutY(0);



        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("M&M Tetris");
        primaryStage.show();

        pane.getChildren().add(rect1);

    }
}
