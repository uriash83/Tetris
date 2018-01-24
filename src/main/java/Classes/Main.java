package Classes;

import com.sun.javafx.collections.MappingChange;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    Random random = new Random();
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
    public static long delayGrav = 400;
    public double r,g,b=  0;
    public static Color color;



    public static List<Coordinate> coordinateActualTetrimino = new ArrayList<>();
    public static Map<Integer,Rectangle> coordinateStatTetrimino = new TreeMap<>();
    public static Map<Integer,Rectangle> coordinateStatTetriminoCopy = new TreeMap<>();
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
                long delayGravity = delayGrav;
                try {
                    Thread.sleep(delayGravity);
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

                    /*
                    reset shifting tetromino because new piece should bi in the same position
                     */
                    MoveTetrimino.shiftLeftRight = 0;
                    //temporary
                    for (int i = 0; i < coordinateActualTetrimino.size(); i++) {
                        System.out.println("RECTANGLE LAYOUT  X " + coordinateActualTetrimino.get(i).getRectangle().getLayoutX() +"RECTANGLE LAYOUT  Y " + coordinateActualTetrimino.get(i).getRectangle().getLayoutY()  );
                        //System.out.println("SIZE " + coordinateActualTetrimino.size() + " AR Y" + coordinateActualTetrimino.get(i).getY() + " X " + coordinateActualTetrimino.get(i).getX());
                        System.out.println("RECTAGLE GETX " + coordinateActualTetrimino.get(i).getRectangle().getX());
                    }
                    /*
                    save actual tetrmino to state matrix before to manage it later (remove )
                     */

                    for (int i = 0; i < coordinateActualTetrimino.size(); i++) {
                        coordinateStatTetrimino.put(coordinateActualTetrimino.get(i).getY()*10+coordinateActualTetrimino.get(i).getX(),
                                coordinateActualTetrimino.get(i).getRectangle());
                        System.out.println("STATE " + coordinateStatTetrimino.size() + " i = " + i);
                    }
                    numberActualTetromino = random.nextInt(7) + 1;
                    //numberActualTetromino+=1;
                    //if(numberActualTetromino>7)
                    //    numberActualTetromino=1;
                    System.out.println("RANDON NUMBER " + numberActualTetromino);

                    r = random.nextFloat();
                    g = random.nextFloat();
                    b = random.nextFloat();
                    color = new Color(r,g,b,1);
                    addNewShape(numberActualTetromino);
                    show(coordinateActualTetrimino);

                    checkAndRemoveLine(theLowestPositionY,theHighestPositionY,coordinateStatTetrimino, coordinateStatTetriminoCopy);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                           addNewTetrminoToPane();
                        }
                    });
                    theHighestPositionY=0;
                    delayGrav = 400;
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
            coordinateActualTetrimino.get(i).getRectangle().setFill(Main.color);
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

    public void addNewTetrminoStaticToPane()
    {

        for (int i = 0; i < coordinateStatTetrimino.size(); i++) {
            pane.getChildren().add(coordinateActualTetrimino.get(i).getRectangle());
        }
    }

    public void checkAndRemoveLine(int theLowestYPosition, int theHighestPositionY,Map<Integer, Rectangle> map , Map<Integer, Rectangle> mapCopy)
    {
        /*
        check and remove line
         */
        int counterLine= 0;
        int layerFromToLower = 0;
        /*
        beyond loop checking coordinateStateMap from theLowestPositon untill not found line contain full tetrimino
         */
        for (int dz = theLowestYPosition; dz < 20; dz++) {
            int counterFullLine = 0;
            System.out.println("The Lowest Y  = " + dz + "TheHisgrest Y = " + theHighestPositionY);
            for (int i = 0; i < 10; i++) {
                if (map.get(dz * 10 + i) != null)
                    counterFullLine += 1;
                if (counterFullLine == 10){ // this condition is executing if line is full of tetromino
                    counterFullLine=0;
                    counterLine+=1;
                    for (int nextLine = dz; nextLine >1 ; nextLine--) {

                        int nextLine1 = nextLine;
                        Platform.runLater(new Runnable() { // removing all tetromino in full line
                            @Override
                            public void run() {
                                for (int j = 0; j < 10; j++) {
                                    pane.getChildren().remove(map.remove((nextLine1 * 10) + j));
                                    System.out.println("REMOVED");
                                }
                            }
                        });
                        mapCopy.clear();// copy map for saving coordintate

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //for (int j = 0; j < 10; j++) {
                        //    System.out.println(" REC BEFORE 0 " + map.get(dz*10+j) + "REC BEFORE  -1 " +  map.get((dz-1)*10+j));
                        //}
                        /*
                        1 stage ;
                        copy line current-1 to current and synchrosnus copy to mapCopy
                         */
                        for (int j = 0; j < 10; j++) {
                            map.put(nextLine * 10 + j, map.get((nextLine - 1) * 10 + j));
                            mapCopy.put(nextLine * 10 + j, map.get((nextLine - 1) * 10 + j));
                        }
                        /*
                        stage 2; removing all tetromino from line current -1;
                         */
                        int nextLine2 = nextLine;
                        for (int j = 0; j < 10; j++) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    for (int j = 0; j < 10; j++) {
                                        pane.getChildren().remove(map.get(((nextLine2 - 1) * 10) + j));
                                        map.remove((nextLine2 - 1) * 10 + j);
                                    }
                                }
                            });

                        }

                        //for (int j = 0; j < 10; j++) {
                        //   System.out.println(" REC AFTER  0 " + map.get(dz*10+j) + "REC  AFTER -1 " +  map.get((dz-1)*10+j));
                        //}
                        /*
                        stage 3; shifting tetrmino form lie current-1 to line current
                         */
                        int nextLine3=  nextLine;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                for (int j = 0; j < 10; j++) {
                                    //map.put(finalDzes*10+j,map.get((finalDzes-1)*10+j));
                                    //map.get(dz*10+j).setLayoutY(300);
                                    if (map.get(nextLine3 * 10 + j) != null) {
                                        double D = mapCopy.get(nextLine3 * 10 + j).getLayoutY();
                                        System.out.println("LEYAOYT ");
                                        map.get(nextLine3 * 10 + j).setLayoutY(D + 25);
                                        pane.getChildren().add(map.get(nextLine3 * 10 + j));
                                    }
                                    //System.out.printf("LAYOUTR %s%n", map.get(dz * 10 + j).getLayoutY());

                                }
                            }
                        });
                        System.out.println("FIZNAL DZEZ "  + nextLine);

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }




                       // System.out.println("SIZE MAPCOPY" + mapCopy.size() + "SIZE MAP" + map.size());


                }
                else {
                    //System.out.println("COUNTER =" + counter);
                }

            }

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
        runClock();

        initialize.initalizeShapeOfTeriminoRotate(mapOfRotateTetrimino);
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
        System.out.println("COLOR "+ r + " " + g + " " + b);
        Main.color = new Color(r,g,b,1);
        addNewShape(numberActualTetromino);
        show(coordinateActualTetrimino);
        pane.getChildren().addAll(clock);
        addNewTetrminoToPane();
        initalizeBorderMap(coordinateStatTetrimino);






    }




}
