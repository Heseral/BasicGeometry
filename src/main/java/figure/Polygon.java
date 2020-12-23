package figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon implements Figure {
    private List<Line> lines = new ArrayList<>();

    public Polygon(List<Line> lines, boolean safety) {
        if (!safety) {
            setLines(lines);
            return;
        }

        if (lines.size() < 3) {
            throw new IllegalArgumentException("An attempt to create a polygon with only " + lines.size() + " lines.");
        }

        for (int i = 1; i < lines.size(); i++) {
            if (!lines.get(i).getFirstPoint().equals(lines.get(i - 1).getSecondPoint())) {
                throw new IllegalArgumentException("Wrong lines: the second " + lines.get(i) +
                        " doesn't start from the end of the first " + lines.get(i) + ".");
            }
        }
    }

    public Polygon(boolean safety, Line... lines) {
        this(Arrays.asList(lines), safety);
    }

    public Polygon(boolean safety, List<Point> points) {
        if (!safety) {
            for (int i = 1; i < points.size(); i++) {
                getLines().add(new Line(points.get(i), points.get(i - 1)));
            }
            return;
        }

        if (points.size() < 3) {
            throw new IllegalArgumentException("An attempt to create a polygon with only " + points.size() + " points.");
        }
        for (int i = 1; i < points.size(); i++) {
            getLines().add(new Line(points.get(i), points.get(i - 1)));
        }
    }

    public Polygon(Point... points) {
        this(true, points);
    }

    public Polygon(List<Point> points) {
        this(true, points);
    }

    public Polygon(boolean safety, Point... points) {
        this(safety, Arrays.asList(points));
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
    public boolean intersects(Figure figure) {
        return figure.intersects(this);
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
    public double isSuperimposedOn(Figure figure) {
        return figure.isSuperimposedOn(this);
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
                if (firstPolygonLine.isSuperimposedOn(secondPolygonLine) > 0) {
                    result += firstPolygonLine.isSuperimposedOn(secondPolygonLine);
                    continue;
                }
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
