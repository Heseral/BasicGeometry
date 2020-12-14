public interface Figure {
    default boolean isPointBelongsToFigure(Point point) {
        return isPointBelongsToFigure(point.getX(), point.getY());
    }

    boolean isPointBelongsToFigure(double x, double y);

    boolean intersects(Point point);

    boolean intersects(Line line);

    boolean intersects(Polygon polygon);

    double isSuperimposedOn(Point point);

    double isSuperimposedOn(Line line);

    double isSuperimposedOn(Polygon polygon);
}
