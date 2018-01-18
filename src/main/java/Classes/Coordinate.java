package Classes;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Coordinate {
    public int x;
    public int y;
    public Rectangle rectangle;
    public boolean freePlace;

    public Coordinate(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Coordinate(int x, int y, Rectangle rectangle) {
        this.x = x;
        this.y = y;
        this.rectangle = rectangle;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {

        return rectangle;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y &&
                Objects.equals(rectangle, that.rectangle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y, rectangle);
    }
}
