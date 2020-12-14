import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon implements Figure {
    private List<Line> lines = new ArrayList<>();

    public Polygon(boolean safety, Line... lines) {
        if (!safety) {
            setLines(Arrays.asList(lines));
            return;
        }

        if (lines.length < 3) {
            throw new IllegalArgumentException("An attempt to create a polygon with only " + lines.length + " lines.");
        }

        for (int i = 1; i < lines.length; i++) {
            if (!lines[i].getFirstPoint().equals(lines[i - 1].getSecondPoint())) {
                throw new IllegalArgumentException("Wrong lines: the second " + lines[i] +
                        " doesn't start from the end of the first " + lines[i] + ".");
            }
        }
    }

    public Polygon(boolean safety, Point... points) {
        if (!safety) {
            for (int i = 1; i < points.length; i++) {
                getLines().add(new Line(points[i], points[i - 1]));
            }
            return;
        }

        if (points.length < 3) {
            throw new IllegalArgumentException("An attempt to create a polygon with only " + points.length + " points.");
        }
        for (int i = 1; i < points.length; i++) {
            getLines().add(new Line(points[i], points[i - 1]));
        }
    }

    public Polygon(Line... lines) {
        this(true, lines);
    }

    @Override
    public String toString() {
        return super.toString() + " of size: " + lines.size();
    }

    public boolean equals(Polygon polygon) {
        return polygon.getLines().equals(getLines());
    }

    @Override
    public boolean isPointBelongsToFigure(double x, double y) {
        for (Line line : lines) {
            if (line.isPointBelongsToFigure(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean intersects(Point point) {
        for (Line line : getLines()) {
            if (line.intersects(point)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean intersects(Line lineToCheck) {
        for (Line line : getLines()) {
            if (line.intersects(lineToCheck)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean intersects(Polygon polygon) {
        for (Line firstLine : getLines()) {
            for (Line secondLine : polygon.getLines()) {
                if (firstLine.intersects(secondLine)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public double isSuperimposedOn(Point point) {
        return 0;
    }

    @Override
    public double isSuperimposedOn(Line line) {
        int result = 0;
        for (Line polygonLine : getLines()) {
            result += line.isSuperimposedOn(polygonLine);
        }
        return result;
    }

    @Override
    public double isSuperimposedOn(Polygon polygon) {
        int result = 0;
        for (Line firstPolygonLine : getLines()) {
            for (Line secondPolygonLine : polygon.getLines()) {
                result += firstPolygonLine.isSuperimposedOn(secondPolygonLine);
            }
        }
        return result;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
