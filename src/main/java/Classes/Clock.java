package Classes;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Clock  extends Parent{
    //private Label clock =new Label();
    private Text clock = new Text();

    public Clock()
    {
        clock.setLayoutX(280);
        clock.setLayoutY(70);
        clock.prefHeight(55);
        clock.prefWidth(105);
        getChildren().add(clock);
    }

    public void setClock(String s)
    {
        clock.setText(s);
    }

}
