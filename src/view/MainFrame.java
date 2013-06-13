package view;

import model.FigureModel;
import model.FigureType;
import util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MainFrame extends JFrame {
    private final FigureTableModel figureTableModel;
    private FigureModel figureModel;
    private final JTable table;

    public MainFrame() throws HeadlessException {
        super("Figure property editor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem closeMenuItem = new JMenuItem("Close");
        closeMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        fileMenu.add(closeMenuItem);

        JMenu modelMenu = new JMenu("Model");

        JMenuItem createRectangleMenuItem = new JMenuItem("Create Rectangle");
        createRectangleMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogFactory.buildCreateFigureDialog(MainFrame.this, figureModel, figureTableModel, FigureType.RECTANGLE).setVisible(true);
            }
        });

        JMenuItem createOvalMenuItem = new JMenuItem("Create Oval");
        createOvalMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogFactory.buildCreateFigureDialog(MainFrame.this, figureModel, figureTableModel, FigureType.OVAL).setVisible(true);
            }
        });

        JMenuItem removeMenuItem = new JMenuItem("Remove");
        removeMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int[] selectedRows = table.getSelectedRows();
                Arrays.sort(selectedRows);
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    figureModel.remove(selectedRows[i]);
                    figureTableModel.fireTableRowsDeleted(i, i);
                }
            }
        });

        modelMenu.add(createRectangleMenuItem);
        modelMenu.add(createOvalMenuItem);
        modelMenu.add(removeMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(modelMenu);
        setJMenuBar(menuBar);

        setLayout(new BorderLayout());
        figureModel = new FigureModel();
        figureTableModel = new FigureTableModel(figureModel);
        table = new JTable(figureTableModel);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    DialogFactory.buildEditFigureDialog(MainFrame.this, figureModel, figureTableModel, row).setVisible(true);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        UIUtil.adjustPositionToCenter(this);
    }
}
