package model;

public enum FigureType {
    RECTANGLE("Rectangle"), OVAL("Oval");

    private String stringValue;

    FigureType(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
