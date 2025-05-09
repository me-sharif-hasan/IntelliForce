package com.iishanto.ide.action;

import com.iishanto.salesforce.deployment.DeployApexClass;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DeployChangedFiles extends AnAction {
    public DeployChangedFiles() {
        super("Deploy This File", "Deploy this file to salesforce", new ImageIcon("/icons/deploy.svg"));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // Call your method here
        System.out.println("Button clicked!");
        //get active file

        VirtualFile virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        if (virtualFile != null) {
            System.out.println("Active file: " + virtualFile.getPath());
            DeployApexClass.getInstance().deploy(virtualFile.getPath(),e.getProject());
        } else {
            System.out.println("No active file found.");
        }
    }
}
