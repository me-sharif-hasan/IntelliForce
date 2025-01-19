package com.iishanto.ide.action;

import com.iishanto.ide.ui.ApexClassCreationDialog;
import com.intellij.codeInsight.intention.impl.CreateClassDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class CreateNewApexClassAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        ApexClassCreationDialog dialog = new ApexClassCreationDialog();
        if (dialog.showAndGet()) { // Show the dialog and check if OK was pressed
            String className = dialog.getClassName();
            String classType = dialog.getClassType();
            // Handle the input (e.g., create a file, generate content)
            System.out.println("Class Name: " + className + ", Class Type: " + classType);
        }
    }
}
