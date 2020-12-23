package figure;

public class Line implements Figure {
    private Point firstPoint;
    private Point secondPoint;

    public Line(Point firstPoint, Point secondPoint) {
        setFirstPoint(firstPoint);
        setSecondPoint(secondPoint);
    }

    @Override
    public String toString() {
        return "Figure.Line(" + getFirstPoint() + " --> " + getSecondPoint() + ")";
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
    public boolean intersects(Figure figure) {
        return figure.intersects(this);
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
    public double isSuperimposedOn(Figure figure) {
        return figure.isSuperimposedOn(this);
    }

    @Override
    public double isSuperimposedOn(Point point) {
        return 0;
    }

    @Override
    public double isSuperimposedOn(Line line) {
        double dX1 = (getSecondPoint().getX() - getFirstPoint().getX());
        double dX2 = (line.getSecondPoint().getX() - line.getFirstPoint().getX());
        double dY1 = getSecondPoint().getY() - getFirstPoint().getY();
        double dY2 = line.getSecondPoint().getY() - line.getFirstPoint().getY();

        double k1 = dY1 / dX1;
        double k2 = dY2 / dX2;

        // линии с разными коэффициентами наклона точно не накладываются, но могут пересекаться. В прочем, это нас
        // не должно волновать.
        if (Math.abs(k1 - k2) > 1e-16) {
            return 0;
        }

        // отсортируем точки для удобства
        Point firstLineFirstPoint = getFirstPoint();
        Point firstLineSecondPoint = getSecondPoint();
        Point secondLineFirstPoint = line.getFirstPoint();
        Point secondLineSecondPoint = line.getSecondPoint();
        if (k1 < 1e-16) {
            // нужна ли сортировка точек первой линии? (первая точка будет левее второй)
            if (firstLineFirstPoint.getX() > firstLineSecondPoint.getX()) {
                firstLineFirstPoint = getSecondPoint();
                firstLineSecondPoint = getFirstPoint();
            }
            // нужна ли сортировка точек второй линии? (первая точка будет левее второй)
            if (secondLineFirstPoint.getX() > secondLineSecondPoint.getX()) {
                secondLineFirstPoint = line.getSecondPoint();
                secondLineSecondPoint = line.getFirstPoint();
            }
            // нужна ли сортировка линий? (первая точка первой линии будет левее первой точки второй линии)
            if (firstLineFirstPoint.getX() > secondLineFirstPoint.getX()) {
                Point temp = firstLineFirstPoint;
                firstLineFirstPoint = secondLineFirstPoint;
                secondLineFirstPoint = temp;
                temp = firstLineSecondPoint;
                firstLineSecondPoint = secondLineSecondPoint;
                secondLineSecondPoint = temp;
            }
        } else {
            // нужна ли сортировка точек первой линии? (первая точка будет ниже второй)
            if (firstLineFirstPoint.getY() > firstLineSecondPoint.getY()) {
                firstLineFirstPoint = getSecondPoint();
                firstLineSecondPoint = getFirstPoint();
            }
            // нужна ли сортировка точек второй линии? (первая точка будет ниже второй)
            if (secondLineFirstPoint.getY() > secondLineSecondPoint.getY()) {
                secondLineFirstPoint = line.getSecondPoint();
                secondLineSecondPoint = line.getFirstPoint();
            }
            // нужна ли сортировка линий? (первая точка первой линии будет ниже первой точки второй линии)
            if (firstLineFirstPoint.getY() > secondLineFirstPoint.getY()) {
                Point temp = firstLineFirstPoint;
                firstLineFirstPoint = secondLineFirstPoint;
                secondLineFirstPoint = temp;
                temp = firstLineSecondPoint;
                firstLineSecondPoint = secondLineSecondPoint;
                secondLineSecondPoint = temp;
            }
        }

        // может они параллельны Ох?
        if ((Math.abs(dY1) < 1e-16 || Math.abs(dY2) < 1e-16)
                && (Math.abs(firstLineFirstPoint.getY() - secondLineFirstPoint.getY()) > 1e-16)) {
            return 0;
        }

        // может они параллельны Оу?
        if ((Math.abs(dX1) < 1e-16 || Math.abs(dX2) < 1e-16)
                && (Math.abs(firstLineFirstPoint.getX() - secondLineFirstPoint.getX()) > 1e-16)) {
            return 0;
        }

        // осталась последняя проверка - проверить высоту линий(коэффициент b). Для этого придется составлять уравнения
        if (firstLineFirstPoint.getX() / dX1 - firstLineFirstPoint.getY() / dY1
                - secondLineFirstPoint.getX() / dX2 + secondLineFirstPoint.getY() / dY2
                > 1e-16) {
            return 0;
        }

        return Math.sqrt(
                Math.pow(secondLineFirstPoint.getX() - firstLineSecondPoint.getX(), 2)
                        + Math.pow(secondLineFirstPoint.getY() - firstLineSecondPoint.getY(), 2)
        );
    }

    @Override
    public double isSuperimposedOn(Polygon polygon) {
        return polygon.isSuperimposedOn(this);
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
