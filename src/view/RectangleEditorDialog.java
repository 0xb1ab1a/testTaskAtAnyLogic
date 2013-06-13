package view;

import model.FigureModel;
import model.Rectangle;
import util.FormBuilder;
import util.ValidationHelper;

import javax.swing.*;
import java.util.List;

public class RectangleEditorDialog extends FigureEditorDialog<Rectangle> {

    private JTextField widthTextField;
    private JTextField heightTextField;

    protected RectangleEditorDialog(JFrame frame, FigureModel figureModel, FigureTableModel figureTableModel, boolean creation, Rectangle figure, int indexToEdit) {
        super(frame, figureModel, figureTableModel, creation, figure, indexToEdit);
        newFigure = new Rectangle();
    }

    @Override
    protected FormBuilder getFormBuilder() {
        widthTextField = new JTextField(String.valueOf(oldFigure.getWidth()), 20);
        heightTextField = new JTextField(String.valueOf(oldFigure.getHeight()), 20);
        return super.getFormBuilder().add("Width:", widthTextField).add("Height:", heightTextField);
    }

    @Override
    protected List<String> getValidationErrors() {
        final List<String> errors = super.getValidationErrors();
        if (!ValidationHelper.isPositiveInteger(widthTextField.getText()))
            errors.add("Width should be positive integer number");
        if (!ValidationHelper.isPositiveInteger(heightTextField.getText()))
            errors.add("Height should be positive integer number");
        return errors;
    }

    @Override
    protected Rectangle getFigure() {
        Rectangle r = super.getFigure();
        r.setWidth(Integer.valueOf(widthTextField.getText()));
        r.setHeight(Integer.valueOf(heightTextField.getText()));
        return r;
    }
}
