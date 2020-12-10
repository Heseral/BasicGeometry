public class Point implements Figure {
    private double x;
    private double y;

    public Point(double x, double y) {
        setX(x);
        setY(y);
    }

    @Override
    public String toString() {
        return "Point(" + getX() + "; " + getY() + ")";
    }

    public boolean equals(Point point) {
        return point.getX() == getX() && point.getY() == getY();
    }


    @Override
    public boolean isPointBelongsToFigure(double x, double y) {
        return getX() == x && getY() == y;
    }

    @Override
    public boolean intersects(Figure figure) {
        return figure.isPointBelongsToFigure(this);
    }

    @Override
    public boolean intersects(Point point) {
        return equals(point);
    }

    @Override
    public boolean intersects(Line line) {
        return line.isPointBelongsToFigure(this);
    }

    @Override
    public boolean intersects(Polygon polygon) {
        return polygon.isPointBelongsToFigure(this);
    }

    @Override
    public int isSuperimposedOn(Figure figure) {
        return figure.isPointBelongsToFigure(this) ? 0 : -1;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
