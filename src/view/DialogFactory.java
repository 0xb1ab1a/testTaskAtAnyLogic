package view;

import model.*;

import javax.swing.*;

public class DialogFactory {
    private DialogFactory() {
    }

    public static FigureEditorDialog buildCreateFigureDialog(JFrame frame, FigureModel figureModel, FigureTableModel figureTableModel, FigureType figureType) {
        switch (figureType) {
            case RECTANGLE:
                return new RectangleEditorDialog(frame, figureModel, figureTableModel, true, new Rectangle(), -1);
            case OVAL:
                return new OvalEditorDialog(frame, figureModel, figureTableModel, true, new Oval(), -1);
            default:
                throw new IllegalArgumentException();
        }
    }

    public static FigureEditorDialog buildEditFigureDialog(JFrame frame, FigureModel figureModel, FigureTableModel figureTableModel, int indexToEdit) {
        Figure figure = figureModel.getFigure(indexToEdit);
        if (figure instanceof Rectangle) {
            return new RectangleEditorDialog(frame, figureModel, figureTableModel, false, (Rectangle) figure, indexToEdit);
        }
        if (figure instanceof Oval) {
            return new OvalEditorDialog(frame, figureModel, figureTableModel, false, (Oval) figure, indexToEdit);
        }
        throw new IllegalArgumentException();
    }
}
