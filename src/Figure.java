public interface Figure {
    default boolean isPointBelongsToFigure(Point point) {
        return isPointBelongsToFigure(point.getX(), point.getY());
    }

    boolean isPointBelongsToFigure(double x, double y);


    boolean intersects(Figure figure);

    int isSuperimposedOn(Figure figure);
}
