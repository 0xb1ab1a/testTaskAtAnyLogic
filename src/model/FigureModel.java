package model;

import java.util.ArrayList;
import java.util.List;

public class FigureModel {
    private List<Figure> figures;

    public FigureModel() {
        figures = new ArrayList<Figure>();
    }

    public Figure getFigure(int index) {
        return figures.get(index);
    }

    public int getFigureCount() {
        return figures.size();
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    public void setFigure(int index, Figure figure) {
        figures.set(index, figure);
    }

    public void remove(int index) {
        figures.remove(index);
    }
}
