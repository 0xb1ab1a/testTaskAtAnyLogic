package view;

import model.Figure;
import model.FigureModel;

import javax.swing.table.DefaultTableModel;

public class FigureTableModel extends DefaultTableModel {
    private static String[] columnNames = new String[]{"Name", "Type", "X", "Y"};
    private FigureModel figureModel;

    public FigureTableModel(FigureModel figureModel) {
        super();
        this.figureModel = figureModel;
    }

    @Override
    public int getRowCount() {
        if (figureModel == null) return 0;
        return figureModel.getFigureCount();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Figure f = figureModel.getFigure(row);
        switch (column) {
            case 0:
                return f.getName();
            case 1:
                return f.getType();
            case 2:
                return f.getX();
            case 3:
                return f.getY();
            default:
                return null;
        }
    }
}
