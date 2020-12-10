public interface Figure {
    default boolean isPointBelongsToFigure(Point point) {
        return isPointBelongsToFigure(point.getX(), point.getY());
    }

    boolean isPointBelongsToFigure(double x, double y);


    default boolean intersects(Figure figure) {
        return intersects(figure.getClass().cast(figure));
    }

    boolean intersects(Point point);

    boolean intersects(Line line);

    boolean intersects(Polygon polygon);

    int isSuperimposedOn(Figure figure);
}
