package view;

import model.FigureModel;
import model.Oval;
import util.FormBuilder;
import util.ValidationHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class OvalEditorDialog extends FigureEditorDialog<Oval> {

    private JTextField radiusXTextField;
    private JTextField radiusYTextField;
    private JCheckBox isCircleCheckBox;
    private JLabel radiusXLabel;
    private JLabel radiusYLabel;

    protected OvalEditorDialog(JFrame frame, FigureModel figureModel, FigureTableModel figureTableModel, boolean creation, Oval figure, int indexToEdit) {
        super(frame, figureModel, figureTableModel, creation, figure, indexToEdit);
        newFigure = new Oval();
    }

    @Override
    protected FormBuilder getFormBuilder() {
        radiusXTextField = new JTextField(String.valueOf(oldFigure.getRadiusX()), 20);
        radiusYTextField = new JTextField(String.valueOf(oldFigure.getRadiusY()), 20);
        isCircleCheckBox = new JCheckBox("Is Circle", oldFigure.getRadiusX() == oldFigure.getRadiusY());
        isCircleCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                final Object source = e.getItemSelectable();
                if (source == isCircleCheckBox) {
                    refreshComponents();
                }
            }
        });
        radiusYLabel = new JLabel("RadiusY:");
        radiusXLabel = new JLabel("RadiusX:") {
            @Override
            public Dimension getPreferredSize() {
                return radiusYLabel.getPreferredSize();
            }
        };
        refreshComponents();
        return super.getFormBuilder().add(isCircleCheckBox).add(radiusXLabel, radiusXTextField).add(radiusYLabel, radiusYTextField);
    }

    private void refreshComponents() {
        if (isCircleCheckBox.isSelected()) {
            radiusXLabel.setText("Radius:");
            radiusYLabel.setVisible(false);
            radiusYTextField.setVisible(false);
        } else {
            radiusXLabel.setText("RadiusX:");
            radiusYLabel.setVisible(true);
            radiusYTextField.setVisible(true);
        }
        pack();
    }

    @Override
    protected List<String> getValidationErrors() {
        final List<String> errors = super.getValidationErrors();
        if (isCircleCheckBox.isSelected()) {
            if (!ValidationHelper.isPositiveInteger(radiusXTextField.getText()))
                errors.add("Radius should be positive integer number");
        } else {
            if (!ValidationHelper.isPositiveInteger(radiusXTextField.getText()))
                errors.add("RadiusX should be positive integer number");
            if (!ValidationHelper.isPositiveInteger(radiusYTextField.getText()))
                errors.add("RadiusY should be positive integer number");
        }
        return errors;
    }

    @Override
    protected Oval getFigure() {
        Oval o = super.getFigure();
        if (isCircleCheckBox.isSelected()) {
            final Integer radius = Integer.valueOf(radiusXTextField.getText());
            o.setRadiusX(radius);
            o.setRadiusY(radius);
        } else {
            o.setRadiusX(Integer.valueOf(radiusXTextField.getText()));
            o.setRadiusY(Integer.valueOf(radiusYTextField.getText()));
        }
        return o;
    }
}
