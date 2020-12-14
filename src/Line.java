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
    public double isSuperimposedOn(Point point) {
        return 0;
    }

    @Override
    public double isSuperimposedOn(Line line) {
        double dX1 = (getSecondPoint().getX() - getFirstPoint().getX());
        double dX2 = (line.getSecondPoint().getX() - line.getFirstPoint().getX());

        // отсортируем точки для удобства
        Point firstLineFirstPoint = getFirstPoint();
        Point firstLineSecondPoint = getSecondPoint();
        Point secondLineFirstPoint = line.getFirstPoint();
        Point secondLineSecondPoint = line.getSecondPoint();

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

        // может первая прямая параллельна Оу? Тогда вторая тоже должна быть параллельна для наложения.
        // Имеется специфичная проверка во избежания деления на ноль.
        if (Math.abs(dX1) < 1e-16) {
            // может вторая линия не параллельна Оу?
            if (Math.abs(dX2) > 1e-16) {
                return 0;
            }
            // может линии находятся на разных координатах Х?
            if ((getFirstPoint().getX() - line.getFirstPoint().getX()) > 1e-16) {
                return 0;
            }
            // последний шанс на не-наложение линий!
            if (firstLineSecondPoint.getY() < secondLineFirstPoint.getY()) {
                return 0;
            }
            return firstLineSecondPoint.getY() - secondLineFirstPoint.getY();
        }

        double k1 = (getSecondPoint().getY() - getFirstPoint().getY()) / dX1;
        double k2 = (line.getSecondPoint().getY() - line.getFirstPoint().getY())
                / (line.getSecondPoint().getX() - line.getFirstPoint().getX());
        // линии с разными коэффициентами наклона точна не накладываются, но могут пересекаться. В прочем, это нас
        // не должно волновать.
        if (k1 - k2 > 1e-16) {
            return 0;
        }

        return Math.sqrt(
                Math.pow(firstLineSecondPoint.getX() - secondLineFirstPoint.getX(), 2)
                        + Math.pow(firstLineSecondPoint.getY() - secondLineFirstPoint.getY(), 2)
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
