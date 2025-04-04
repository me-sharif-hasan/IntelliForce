package com.iishanto.ide.ui.action;

import com.iishanto.ide.ui.ApexClassPopupPanel;
import com.iishanto.ide.utility.IDEUtility;
import com.iishanto.ide.utility.SalesforceUtility;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;

import java.io.File;
import java.util.Objects;

public class CreateNewApexClassAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;
        VirtualFile projectDir = e.getData(CommonDataKeys.VIRTUAL_FILE);

        ApexClassPopupPanel popupPanel = new ApexClassPopupPanel(project, new ApexClassPopupPanel.OnCreateApexFile() {
            @Override
            public void onCreateApexFile(String fileName, String kind) {
                if (fileName.isEmpty()) {
                    return;
                }
                if(fileName.endsWith(".cls") || fileName.endsWith(".trigger")){
                    fileName = fileName.substring(0, fileName.lastIndexOf('.'));
                }
                //if there is any speicial chars other than / that show error notification
                if(fileName.matches(".*[^a-zA-Z0-9/].*")){
                    SalesforceUtility.showNotification(project,"Invalid file name","File name can only contain alphabets, numbers and /");
                    return;
                }

                String [] fileNameParts = fileName.split("/");
                StringBuilder actionSelectedDirectory = new StringBuilder(Objects.requireNonNull(projectDir).getPath());

                if(fileNameParts.length>1){
                    for (int i = 0; i < fileNameParts.length-1; i++) {
                        String fileNamePart = fileNameParts[i];
                        actionSelectedDirectory.append("/").append(fileNamePart);
                    }
                    File file = new File(actionSelectedDirectory + "/");
                    if (!file.exists()) {
                        if(file.mkdirs()){
                            System.out.println("Directory created successfully");
                            IDEUtility.forceRefresh(projectDir,null);
                        }
                    }
                }

                String dir = actionSelectedDirectory.toString();
                String file = fileNameParts[fileNameParts.length-1];
                actionSelectedDirectory.append("/").append(file);
                if(kind.equals("Class")){
                    SalesforceUtility.createApexClassFile(file,dir,project,projectDir);
                }else if(kind.equals("Trigger")) {
                    SalesforceUtility.createApexTriggerFile(actionSelectedDirectory.toString());
                }
            }
        });

        // Create and show the popup
        JBPopup popup = JBPopupFactory.getInstance()
                .createComponentPopupBuilder(popupPanel, popupPanel.getNameField())
                .setTitle("New Apex File")
                .setMovable(true)
                .setRequestFocus(true)
                .setLocateWithinScreenBounds(true)
                .setResizable(true)
                .setMinSize(popupPanel.getPreferredSize())
                .setCancelOnClickOutside(true)
                .createPopup();

        popup.showCenteredInCurrentWindow(project);
    }
}
