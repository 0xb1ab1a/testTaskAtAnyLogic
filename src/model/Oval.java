package model;

public class Oval extends Figure {
    private int radiusX;
    private int radiusY;

    public int getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(int radiusX) {
        this.radiusX = radiusX;
    }

    public int getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(int radiusY) {
        this.radiusY = radiusY;
    }

    @Override
    public FigureType getType() {
        return FigureType.OVAL;
    }
}
