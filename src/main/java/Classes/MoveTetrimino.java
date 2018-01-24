package Classes;

import java.util.*;

public class MoveTetrimino {

    static int incrRotate = 0;
    static  int shiftLeftRight = 0;
    int theHighestXpositionAfterRotate = 0;
    int theLowestXpositionAfterRotate = 10;
    int theHighestYpositionAfterRotate = 0;
    public void moveShapeonce(List<Coordinate> list)
    {
        for (int i = 0; i < list.size(); i++) {
           list.get(i).setY(list.get(i).getY() + 1);


        }
    }

    public void moveTetriminoRight(List<Coordinate> list)
    {
        // ///checking if X coordinate is >=9 -> out of Matrix
        boolean isOut = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getX()>=9) {
                isOut=true;
                break;
            }
        }
        //if not move tetrimino
        if(!isOut) {
            shiftLeftRight+=1;
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setX(list.get(i).getX() + 1);
            }
        }
    }
    public void moveTetriminoLeftt(List<Coordinate> list)
    {
        // ///checking if X coordinate is <=0 -> out of Matrix
        boolean isOut = false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getX()<=0) {
                isOut=true;
                break;
            }
        }
        //if not move tetrimino
        if(!isOut) {
            shiftLeftRight-=1;
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setX(list.get(i).getX() - 1);
            }
        }
    }

    //public void rotateTetrimino(){
        public void rotateTetrimino(List<Coordinate> list , Map<Integer,Coordinate> map , int averageXposition , int averageYposition){
        incrRotate+=1;
        if(incrRotate>3)
            incrRotate=0;
        System.out.println("ROTATE INT " + shiftLeftRight);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(" Shigt pozistn " + shiftLeftRight + " Old X position " + list.get(i).getX() + " New X position " +(Main.numberActualTetromino*100+incrRotate*10+i) + "Actual number tetr " + Main.numberActualTetromino);
                //System.out.println("Old Y position" + list.get(i).getY() + "New Y position" +(map.get(Main.numberActualTetromino*100+incrRotate*10+i).getY()+averageYposition));
                list.get(i).setX((map.get(Main.numberActualTetromino*100+incrRotate*10+i).getX())+shiftLeftRight);
                list.get(i).setY((map.get(Main.numberActualTetromino*100+incrRotate*10+i).getY())+averageYposition);
                /*
                get the highest X position after rotate
                 */
                if((map.get(Main.numberActualTetromino*100+incrRotate*10+i).getX())+shiftLeftRight > theHighestXpositionAfterRotate)
                {
                    theHighestXpositionAfterRotate=(map.get(Main.numberActualTetromino*100+incrRotate*10+i).getX())+shiftLeftRight;
                }
                /*
                get theLowest Z position after rotate
                 */
                if((map.get(Main.numberActualTetromino*100+incrRotate*10+i).getX())+shiftLeftRight < theLowestXpositionAfterRotate)
                {
                    theLowestXpositionAfterRotate=(map.get(Main.numberActualTetromino*100+incrRotate*10+i).getX())+shiftLeftRight;
                }

            }
            System.out.println("X HIGH ROTATE POSITION AFTER ROTATE  " + theHighestXpositionAfterRotate +"X LOW ROTATE POSITION AFTER ROTATE " + theLowestXpositionAfterRotate);

            if(theHighestXpositionAfterRotate>=9)
            {
                System.out.println(theHighestXpositionAfterRotate + "OUT OF 8");
                int tmp = theHighestXpositionAfterRotate-9;
                for (int i = 0; i < list.size(); i++) {
                    //System.out.println(" Shigt pozistn " + shiftLeftRight + " Old X position " + list.get(i).getX() + " New X position " +(Main.numberActualTetromino*100+incrRotate*10+i) + "Actual number tetr " + Main.numberActualTetromino);
                    list.get(i).setX((map.get(Main.numberActualTetromino*100+incrRotate*10+i).getX())+shiftLeftRight-tmp);
                    list.get(i).setY((map.get(Main.numberActualTetromino*100+incrRotate*10+i).getY())+averageYposition);
                }
                theHighestXpositionAfterRotate=0;
            }
            else if (theLowestXpositionAfterRotate<0) {
                int tmp = -(theLowestXpositionAfterRotate);
                System.out.printf(theLowestXpositionAfterRotate + "OUT 0");
                for (int i = 0; i < list.size(); i++) {
                    //System.out.println(" Shigt pozistn " + shiftLeftRight + " Old X position " + list.get(i).getX() + " New X position " +(Main.numberActualTetromino*100+incrRotate*10+i) + "Actual number tetr " + Main.numberActualTetromino);
                    list.get(i).setX((map.get(Main.numberActualTetromino*100+incrRotate*10+i).getX())+shiftLeftRight+tmp);
                    list.get(i).setY((map.get(Main.numberActualTetromino*100+incrRotate*10+i).getY())+averageYposition);
                }
            }


    }

    public long moveFastTetrminoDown()
    {
        long delayTime;
        delayTime = 40;
        System.out.println("DELAY = " + delayTime);
        return delayTime;
    }
}
