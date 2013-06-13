package util;

import javax.swing.*;
import java.awt.*;

public class FormBuilder {
    private JPanel panel;
    private int lineCount;

    private FormBuilder() {
        panel = new JPanel(new GridBagLayout());
        lineCount = 0;
    }

    public static FormBuilder create() {
        return new FormBuilder();
    }

    public FormBuilder add(String s, JTextField textField) {
        return add(new JLabel(s), textField);
    }

    public FormBuilder add(JLabel label, JTextField textField) {
        return add(label, (JComponent)textField);
    }

    public FormBuilder add(JCheckBox checkBox) {
        return add(new JLabel(), checkBox);
    }

    private FormBuilder add(JLabel label, JComponent component) {
        panel.add(label, new GridBagConstraints(0, lineCount, 1, 1, 0, 0,
                GridBagConstraints.BASELINE_LEADING, GridBagConstraints.NONE, new Insets(2, 10, 2, 10), 0, 0));
        panel.add(component, new GridBagConstraints(1, lineCount, 1, 1, 1, 0,
                GridBagConstraints.BASELINE_LEADING, getFill(component), new Insets(2, 0, 2, 10), 0, 0));
        lineCount++;
        return this;
    }

    private int getFill(JComponent component) {
        if (component instanceof JTextField) {
            return GridBagConstraints.HORIZONTAL;
        }
        return GridBagConstraints.NONE;
    }




    public JPanel getPanel() {
        return panel;
    }

}
