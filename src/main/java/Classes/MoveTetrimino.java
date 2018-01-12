package Classes;

import java.util.List;

public class MoveTetriminoOnce {
    public void moveShapeonce(List<Coordinate> list)
    {
        for (int i = 0; i < list.size(); i++) {
           list.get(i).setY(list.get(i).getY() + 1);


        }
    }
}
