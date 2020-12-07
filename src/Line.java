public class Line implements Figure {
    private Point firstPoint;
    private Point secondPoint;

    public Line(Point firstPoint, Point secondPoint) {
        setFirstPoint(firstPoint);
        setSecondPoint(secondPoint);
    }

    @Override
    public String toString() {
        return "Line(" + getFirstPoint() + " --> " + getSecondPoint() + ")";
    }

    public boolean equals(Line line) {
        return getFirstPoint().equals(line.getFirstPoint()) && getSecondPoint().equals(line.getSecondPoint());
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

    public Point getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }
}
