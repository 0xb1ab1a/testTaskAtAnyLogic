package model;

public abstract class Figure {
    private String name;
    private int x;
    private int y;

    public String getName() {
        return name;
    }

    public abstract FigureType getType();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
