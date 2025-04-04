package com.iishanto.ide.ui;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ApexClassPopupPanel extends JPanel {
    private final CustomTextField nameField;
    private final JBLabel iconLabel;
    private final JBList<String> kindList = new JBList<>("Class", "Trigger","Script");
    private final OnCreateApexFile onCreateApexFile;

    public ApexClassPopupPanel(Project project,OnCreateApexFile onCreateApexFile) {
        this.onCreateApexFile=onCreateApexFile;
        setPreferredSize(new Dimension(400, 150));
        setBackground(JBColor.background());
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = JBUI.insets(8);

        // Name field with icon (Row 0)
        JPanel namePanel = new JPanel(new BorderLayout(5, 0));
        namePanel.setBackground(JBColor.background());

        // Add icon label
        iconLabel = new JBLabel(AllIcons.Nodes.Class);
        iconLabel.setBorder(JBUI.Borders.empty(0, 5));
        namePanel.add(iconLabel, BorderLayout.WEST);

        nameField = new CustomTextField();
        nameField.setBorder(null);
        nameField.setBackground(getBackground());
        nameField.setForeground(JBColor.foreground());
        namePanel.add(nameField, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(namePanel, gbc);

        // Kind list (Row 1)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        kindList.setSelectedIndex(0);
        kindList.setBorder(null);
        kindList.setBackground(JBColor.background());
        kindList.setCellRenderer((list, value, index, isSelected, cellHasFocus) ->
                getListLabel(value, isSelected));
        kindList.setFixedCellHeight(30);
        add(kindList, gbc);

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createApexFile();
                    closePopup();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    closePopup();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
                    renderArrowFocus(e.getKeyCode() == KeyEvent.VK_DOWN);
                }
            }
        });

        kindList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    createApexFile();
                    closePopup();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    closePopup();
                } else if (e.getKeyCode() == KeyEvent.VK_UP && kindList.getSelectedIndex() == 0) {
                    nameField.requestFocus();
                }
            }
        });

        kindList.addListSelectionListener(e -> {
            if (kindList.getSelectedIndex() == 0) {
                iconLabel.setIcon(AllIcons.Nodes.Class);
            } else if(kindList.getSelectedIndex() == 1){
                iconLabel.setIcon(AllIcons.Nodes.ExceptionClass);
            }else {
                iconLabel.setIcon(AllIcons.Nodes.Function);
            }
            nameField.requestFocus();
        });
        SwingUtilities.invokeLater(nameField::requestFocusInWindow);
    }

    public JBTextField getNameField() {
        return nameField;
    }

    private void createApexFile() {
        this.onCreateApexFile.onCreateApexFile(nameField.getText(), kindList.getSelectedValue());
    }

    private void closePopup() {
        SwingUtilities.getWindowAncestor(ApexClassPopupPanel.this).dispose();
    }

    private void renderArrowFocus(boolean isDown) {
        int index = kindList.getSelectedIndex();
        if (isDown) {
            if (index == kindList.getModel().getSize() - 1) {
                kindList.setSelectedIndex(0);
            } else {
                kindList.setSelectedIndex(index + 1);
            }
        } else {
            if (index == 0) {
                kindList.setSelectedIndex(kindList.getModel().getSize() - 1);
            } else {
                kindList.setSelectedIndex(index - 1);
            }
        }
        nameField.requestFocus();
    }

    private JBLabel getListLabel(String value, boolean isSelected) {
        JBLabel label = new JBLabel(value);
        if (value.equals("Class")) {
            label.setIcon(AllIcons.Nodes.Class);
        } else if(value.equals("Script")){
            label.setIcon(AllIcons.Nodes.Function);
        }else {
            label.setIcon(AllIcons.Nodes.ExceptionClass);
        }
        label.setBorder(JBUI.Borders.empty(0, 5));
        label.setForeground(isSelected ? JBColor.BLUE : JBColor.foreground());
        label.setBackground(isSelected ? JBColor.LIGHT_GRAY : JBColor.background());
        label.setOpaque(true);
        return label;
    }

    private static class CustomTextField extends JBTextField {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getText().isEmpty()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(JBColor.GRAY);
                FontMetrics fm = g2.getFontMetrics();
                int x = getInsets().left + 5;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                String placeholder = "Name";
                g2.drawString(placeholder, x, y);
                g2.dispose();
            }
        }
    }

    public interface OnCreateApexFile{
        void onCreateApexFile(String fileName, String kind);
    }
}