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
        return (x - getFirstPoint().getX()) / (getSecondPoint().getX() - getFirstPoint().getX())
                == (y - getFirstPoint().getY()) / (getSecondPoint().getY() - getFirstPoint().getY());
    }

    @Override
    public boolean intersects(Point point) {
        return isPointBelongsToFigure(point);
    }

    @Override
    public boolean intersects(Line line) {
        if (getSecondPoint().getY() - getFirstPoint().getY() != 0) {
            double q = (getSecondPoint().getX() - getFirstPoint().getX())
                    / (getFirstPoint().getY() - getSecondPoint().getY());
            double sn = (line.getFirstPoint().getX() - line.getSecondPoint().getX())
                    + (line.getFirstPoint().getY() - line.getSecondPoint().getY()) * q;
            return sn != 0;
        }
        return (line.getFirstPoint().getY() - line.getSecondPoint().getY()) != 0;
    }

    @Override
    public boolean intersects(Polygon polygon) {
        return polygon.intersects(this);
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
