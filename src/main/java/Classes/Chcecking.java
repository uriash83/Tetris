package Classes;

import java.util.*;
import javafx.scene.shape.Rectangle;

public class Chcecking {
    public void shift()
    {
        System.out.println("shift");
    }

    public  boolean ifNextGravityPlaceIsFree(Map<Integer,Rectangle> map ,  List<Coordinate> list)
    {
        boolean isFree = true;
        for (int i = 0; i < list.size(); i++) {
            if(map.get((list.get(i).getY()+1)*10+list.get(i).getX())==null){
                isFree = true;
            }
            else {
                isFree= false;
                return isFree;
                //break;
            }
        }
        //System.out.println(map.get(149) + "149");
        //System.out.println(map.get(159) +"159");
        //System.out.println(map.get(169) + "169");
        //System.out.println(map.get(179) + "169");
        System.out.println("Next posioton is " + isFree);
        return isFree;
    }

    public int theHighestYPositionActualTetriino(List<Coordinate> list)
    {   int theHighestY = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getY()>theHighestY)
            {
                theHighestY=list.get(i).getY();
            }
        }
        return theHighestY;
    }

    public int theHighestXPositionActualTetriino(List<Coordinate> list)
    {   int theHighestX = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getX()>theHighestX)
            {
                theHighestX=list.get(i).getX();
            }
        }
        return theHighestX;
    }

    public int theLowestYPositionActualTetriino(List<Coordinate> list)
    {   int theLowestY = 50;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getY()<theLowestY)
            {
                theLowestY=list.get(i).getY();
            }
        }
        return theLowestY;
    }

    public int theLowestXPositionActualTetriino(List<Coordinate> list)
    {   int theLowestX = 50;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getY()<theLowestX)
            {
                theLowestX=list.get(i).getY();
            }
        }
        return theLowestX;
    }



}
