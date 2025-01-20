package com.iishanto.ide.modulebuilder.step;

import com.iishanto.common.Logger;
import com.iishanto.common.Utility;
import com.iishanto.ide.modulebuilder.ModuleConfig;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;
import java.awt.*;
public class SalesforceProjectWizardStep extends ModuleWizardStep {

    private final JPanel panel;
    private String selectedCliPath;
    private TextFieldWithBrowseButton sfdxDirectoryField;

    public SalesforceProjectWizardStep() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Salesforce CLI Location");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);

        sfdxDirectoryField = new TextFieldWithBrowseButton();
        sfdxDirectoryField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        sfdxDirectoryField.getTextField().setText(Utility.getSfCliRootFromPath());
        sfdxDirectoryField.addBrowseFolderListener(
                "Select SFDX Directory", "Choose your Salesforce DX directory",
                null,
                new FileChooserDescriptor(false, true, false, false, false, false)
        );
        sfdxDirectoryField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(sfdxDirectoryField);
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

    @Override
    public void updateDataModel() {
        String path=Utility.combineExecutable(sfdxDirectoryField.getText());
        System.out.println("Going next: "+path);
        selectedCliPath=path;
        ModuleConfig.sfCliPath=path;
    }

    public String getSelectedCliPath() {
        return selectedCliPath;
    }
}
