package Controlers;

import Classes.MoveTetrimino;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import Classes.Main;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class PaneControler {
    MoveTetrimino mv = new MoveTetrimino();
    Main main = new Main();
    @FXML
    public Pane pane;

    public PaneControler() {
    }

    @FXML
    public void initialize()
    {
       // pane.setOnKeyPressed(e-> {
        //    System.out.println("Kprassed");
        //});


    }

    public void onKeyPressPane(KeyEvent keyEvent) {

        switch (keyEvent.getCode())
        {
            case A:
                System.out.println("push a  ");
                mv.moveTetriminoLeftt(Main.coordinateActualTetrimino);
                    break;
            case D:
                mv.moveTetriminoRight(Main.coordinateActualTetrimino);
                System.out.println("push b");
                    break;
            case S:
                System.out.println("push s");
                break;
            case W:
                System.out.println("push w");
                break;
            case R:
                mv.rotateTetrimino(Main.coordinateActualTetrimino,Main.mapOfRotateTetrimino,Main.averageTetriminoXPosition,Main.averageTetriminoYPosition);
                System.out.println("sapce");
                break;
        }

       // System.out.println(keyEvent.getCode()+ "1");

    }
}
