package view;

import model.Figure;
import model.FigureModel;
import util.FormBuilder;
import util.UIUtil;
import util.ValidationHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


public abstract class FigureEditorDialog<T extends Figure> extends JDialog {
    private final FigureModel figureModel;

    protected final T oldFigure;
    protected T newFigure;

    private FigureTableModel figureTableModel;
    private final boolean creation;
    private final int indexToEdit;

    private JTextField nameTextField;
    private JTextField xTextField;
    private JTextField yTextField;

    protected FigureEditorDialog(JFrame frame, FigureModel figureModel, FigureTableModel figureTableModel, boolean creation, T figure, int indexToEdit) {
        super(frame, (creation ? "Create " : "Edit ") + figure.getType().toString(), true);
        this.figureModel = figureModel;
        this.figureTableModel = figureTableModel;
        this.creation = creation;
        this.oldFigure = figure;
        this.indexToEdit = indexToEdit;

        getContentPane().setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        final JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        final JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePerformed();
            }
        });
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(getFormBuilder().getPanel(), BorderLayout.NORTH);
        pack();
        UIUtil.adjustPositionToCenter(this);
    }

    private void savePerformed() {
        List<String> validationErrors = getValidationErrors();
        if (validationErrors.isEmpty()) {
            if (creation) {
                figureModel.addFigure(getFigure());
                figureTableModel.fireTableRowsInserted(figureModel.getFigureCount() - 1, figureModel.getFigureCount() - 1);
            } else {
                figureModel.setFigure(indexToEdit, getFigure());
                figureTableModel.fireTableRowsUpdated(indexToEdit, indexToEdit);
            }
            setVisible(false);
            dispose();
        } else {
            StringBuilder message = new StringBuilder();
            for (String validationError : validationErrors) {
                message.append(validationError).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString(), "Validation Problems", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected FormBuilder getFormBuilder() {
        nameTextField = new JTextField(oldFigure.getName());
        xTextField = new JTextField(String.valueOf(oldFigure.getX()), 20);
        yTextField = new JTextField(String.valueOf(oldFigure.getY()), 20);
        return FormBuilder.create().add("Name:", nameTextField).add("X:", xTextField).add("Y:", yTextField);
    }

    protected T getFigure() {
        newFigure.setName(nameTextField.getText());
        newFigure.setX(Integer.parseInt(xTextField.getText()));
        newFigure.setY(Integer.parseInt(yTextField.getText()));
        return newFigure;
    }

    protected List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        if (!ValidationHelper.isInteger(xTextField.getText())) errors.add("X should be integer number");
        if (!ValidationHelper.isInteger(yTextField.getText())) errors.add("Y should be integer number");
        return errors;
    }
}
