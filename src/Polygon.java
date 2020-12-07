import java.util.Arrays;
import java.util.List;

public class Polygon implements Figure {
    private List<Point> points;

    public Polygon(Point... points) {
        if (points.length < 2) {
            throw new IllegalArgumentException("An attempt to create a polygon with only " + points.length + " points.");
        }
        setPoints(Arrays.asList(points));
    }

    @Override
    public String toString() {
        return super.toString() + " of size: " + points.size();
    }

    public boolean equals(Polygon polygon) {
        return polygon.getPoints().equals(getPoints());
    }

    @Override
    public boolean isPointBelongsToFigure(double x, double y) {
        return false;
    }

    @Override
    public boolean intersects(Figure figure) {
        return false;
    }

    @Override
    public int isSuperimposedOn(Figure figure) {
        return 0;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
