package com.iishanto.ide.ui;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ApexClassCreationDialog extends DialogWrapper {
    private JTextField classNameField;
    private JComboBox<String> classTypeComboBox;

    public ApexClassCreationDialog() {
        super(true);
        init();
        setTitle("Create New Java Class");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Class Name:"));
        classNameField = new JTextField(20);
        classNameField.setBounds(0,0,200,50);
        namePanel.add(classNameField);
        contentPanel.add(namePanel);

        JPanel typePanel = new JPanel();
        typePanel.add(new JLabel("Class Type:"));
        classTypeComboBox = new ComboBox<>(new String[]{"Class", "Interface", "Enum", "Annotation"});
        classTypeComboBox.setBounds(0,0,200,50);
        typePanel.add(classTypeComboBox);
        contentPanel.add(typePanel);

        return contentPanel;
    }

    public String getClassName() {
        return classNameField.getText();
    }

    public String getClassType() {
        return (String) classTypeComboBox.getSelectedItem();
    }
}
