package Classes;

import com.sun.javafx.collections.MappingChange;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Main extends Application {
    public static final int MULTIPLY = 25;
    LocalTime time;
    Clock clock = new Clock();
    Rectangle r1;
    Rectangle r2;
    Pane pane;
    static  int theHighestPositionY = 0;
    static int theLowestPositionY = 50;
    static  int theHighestPositionX = 0;
    static int theLowestPositionX = 50;
    public static int averageTetriminoXPosition = 0;
    public static int averageTetriminoYPosition = 0;
    //public static int number = 1;
    MoveTetrimino mv = new MoveTetrimino();
    Chcecking chcecking = new Chcecking();
    Initialize initialize = new Initialize();
    static int numberActualTetromino  = 1;



    public static List<Coordinate> coordinateActualTetrimino = new ArrayList<>();
    public static Map<Integer,Rectangle> coordinateStatTetrimino = new TreeMap<>();
    public static Map<Integer,Coordinate> mapOfRotateTetrimino = new TreeMap<>();



    public static void main(String[] args) {
        launch(args);



    }


    public void runClock()
    {
        new Thread(() -> {
            while (true) {
                time = LocalTime.now();
                clock.setClock(time.toString());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*
                calculating the average position of tetromino
                 */
                theHighestPositionY = chcecking.theHighestYPositionActualTetriino(coordinateActualTetrimino);
                theLowestPositionY = chcecking.theLowestYPositionActualTetriino(coordinateActualTetrimino);
                theHighestPositionX = chcecking.theHighestXPositionActualTetriino(coordinateActualTetrimino);
                theLowestPositionX = chcecking.theLowestXPositionActualTetriino(coordinateActualTetrimino);
                System.out.println("the highest Y=  " + theHighestPositionY+ "the lowest Y= " + theLowestPositionY
                    + "the hishest X= " + theHighestPositionX + "the lowest X= " + theLowestPositionX
                );
                averageTetriminoXPosition = (theHighestPositionX+theLowestPositionX)/2;
                averageTetriminoYPosition = (theHighestPositionY+theLowestPositionY)/2;
                System.out.printf("average x " + averageTetriminoXPosition + " average Y" + averageTetriminoYPosition);
                /*
                checking if there is possible nezt move
                 */
                //if(!(theHighestPositionY>=19 )) {
                if(chcecking.ifNextGravityPlaceIsFree(coordinateStatTetrimino,coordinateActualTetrimino)){
                        mv.moveShapeonce(coordinateActualTetrimino);
                    System.out.println("IFFFFFFFFFFFFFFFF>190");
                }
                else{
                    theHighestPositionY=0;
                    /*
                    reset shifting tetromino because new piece should bi in the same position
                     */
                    MoveTetrimino.shiftLeftRight = 0;
                    //temporary
                    for (int i = 0; i < coordinateActualTetrimino.size(); i++) {
                        System.out.println("AR Y" + coordinateActualTetrimino.get(i).getY() + " X " + coordinateActualTetrimino.get(i).getX());
                        //System.out.println("AR"+arrayAllTetrimino[coordinateActualTetrimino.get(i).getY()][coordinateActualTetrimino.get(i).getX()]);


                    }
                    /*
                    save actual tetrmino to state matrix before to manage it later (remove )
                     */
                    for (int i = 0; i < coordinateActualTetrimino.size(); i++) {
                        coordinateStatTetrimino.put(coordinateActualTetrimino.get(i).getY()*10+coordinateActualTetrimino.get(i).getX(),
                                coordinateActualTetrimino.get(i).getRectangle());
                    }

                    numberActualTetromino+=1;
                    if(numberActualTetromino>7)
                        numberActualTetromino=1;
                    System.out.println(numberActualTetromino);
                    addNewShape(numberActualTetromino);
                    show(coordinateActualTetrimino);
                    chcecking.ifNextGravityPlaceIsFree(coordinateStatTetrimino,coordinateActualTetrimino);
                    //Platform.runLater(new Runnable() {
                    //    @Override
                    //    public void run() {
                    //         pane.getChildren().remove(coordinateStatTetrimino.remove(169));
                    //         pane.getChildren().remove(coordinateStatTetrimino.remove(179));
                    //         pane.getChildren().remove(coordinateStatTetrimino.remove(189));
                    //         pane.getChildren().remove(coordinateStatTetrimino.remove(199));
                    //        }
                    //    });
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            addNewTetrminoToPane();
                        }
                    });

                }
                System.out.println("SIZE " + coordinateActualTetrimino.size());
                show(coordinateActualTetrimino);

            }
        }).start();


    }
    public void addNewShape(int numberofTetromino)
    {
        coordinateActualTetrimino.clear();
        coordinateActualTetrimino.add(new Coordinate(4,0,new Rectangle(25,25,Color.BLUE)));
        coordinateActualTetrimino.add(new Coordinate(4,1,new Rectangle(25,25,Color.BLUE)));
        coordinateActualTetrimino.add(new Coordinate(4,2,new Rectangle(25,25,Color.BLUE)));
        coordinateActualTetrimino.add(new Coordinate(3,2,new Rectangle(25,25,Color.BLUE)));
        for (int i = 0; i < coordinateActualTetrimino.size(); i++) {
            coordinateActualTetrimino.get(i).setX(mapOfRotateTetrimino.get(numberofTetromino*100+i).getX());
            coordinateActualTetrimino.get(i).setY(mapOfRotateTetrimino.get(numberofTetromino*100+i).getY());
        }


    }

    public void show(List<Coordinate> list)
    {
        for (int i = 0; i < list.size() ; i++) {
            list.get(i).getRectangle().setLayoutY(list.get(i).getY()*MULTIPLY);
            list.get(i).getRectangle().setLayoutX(list.get(i).getX()*MULTIPLY);
        }
    }

    public void addNewTetrminoToPane()
    {

        for (int i = 0; i < coordinateActualTetrimino.size(); i++) {
            pane.getChildren().add(coordinateActualTetrimino.get(i).getRectangle());
        }
    }

    public void reemov()
    {

        for (int i = 0; i < coordinateActualTetrimino.size(); i++) {
            pane.getChildren().remove(coordinateActualTetrimino.get(i).getRectangle());



        }
    }

    public void initalizeBorderMap(Map<Integer,Rectangle> map)
    {
        map.put(200,new Rectangle(25,25,Color.RED));
        map.put(201,new Rectangle(25,25,Color.RED));
        map.put(202,new Rectangle(25,25,Color.RED));
        map.put(203,new Rectangle(25,25,Color.RED));
        map.put(204,new Rectangle(25,25,Color.RED));
        map.put(205,new Rectangle(25,25,Color.RED));
        map.put(206,new Rectangle(25,25,Color.RED));
        map.put(207,new Rectangle(25,25,Color.RED));
        map.put(208,new Rectangle(25,25,Color.RED));
        map.put(209,new Rectangle(25,25,Color.RED));
    }



    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/PaneWindow.fxml"));
        pane = loader.load();

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("M&M Tetris");
        primaryStage.show();



        //pane.getChildren().add(rect2);

        runClock();

        initialize.initalizeShapeOfTeriminoRotate(mapOfRotateTetrimino);
        addNewShape(numberActualTetromino);
        show(coordinateActualTetrimino);
        pane.getChildren().add(clock);
        addNewTetrminoToPane();
        initalizeBorderMap(coordinateStatTetrimino);






    }




}
