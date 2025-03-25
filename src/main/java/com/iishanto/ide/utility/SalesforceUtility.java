package com.iishanto.ide.utility;

import com.google.common.base.Charsets;
import com.iishanto.ide.config.SalesforceProjectConfig;
import com.iishanto.language.sf.ApexIcons;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessListener;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static com.intellij.configurationStore.StorageUtilKt.NOTIFICATION_GROUP_ID;

public class SalesforceUtility {

    private static String getFileDir(String fileName){
        return fileName.substring(0,fileName.lastIndexOf('/'));
    }
    private static String getFileTitle(String fileName){
        String fileTitle=fileName.substring(fileName.lastIndexOf('/')+1);
        return fileTitle.substring(0,fileTitle.lastIndexOf('.'));
    }

    public static void createApexClassFile(String fileName,String location, Project project, VirtualFile projectDir) {
        // Create the file
        String sfCliPath = SalesforceProjectConfig.getInstance(project).getSfCliPath();
        //sf apex generate class --name myClass --output-dir force-app/main/default/classes
        IDEUtility.SalesforceOutputManager outputManager = IDEUtility.getSalesforceOutputManager(project);
        VirtualFile fileDirVf = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(new File(location));
        System.out.println("Creating apex class: "+fileName+" in "+location+" GIVEN "+fileName);
        GeneralCommandLine commandLine = new GeneralCommandLine(sfCliPath, "apex", "generate", "class", "--name", fileName, "--output-dir", location)
                .withWorkDirectory(project.getBasePath())
                .withCharset(Charsets.UTF_8);
        try {
            OSProcessHandler processHandler = new OSProcessHandler(commandLine);
            processHandler.addProcessListener(new ProcessListener() {
                @Override
                public void processTerminated(@NotNull ProcessEvent event) {
                    if(fileDirVf!=null){
                        System.out.println("Apex class created successfully");
                        submitForIndexing(project,fileDirVf);
                        IDEUtility.forceRefreshAndExpand(fileDirVf, project,null);
                    }
                    showNotification(project, "New apex class created", "Apex class is created in: %s with metadata.".formatted(fileName));
                }

                @Override
                public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                    System.out.println(event.getText());
                    outputManager.log(event.getText());
                }
            });
            processHandler.startNotify();
            outputManager.attachToProcess(processHandler, "Creating Apex Class");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showNotification(Project project, String title, String content){
        Notification notification = new Notification(NOTIFICATION_GROUP_ID, title, content, NotificationType.INFORMATION);
        notification.setIcon(ApexIcons.APEX_ICON);
        ApplicationManager.getApplication().invokeLater(() -> Notifications.Bus.notify(notification, project));
    }

    public static void createApexTriggerFile(String string) {
    }


    private static void submitForIndexing(Project project,VirtualFile file){
        ApplicationManager.getApplication().invokeLater(()->{
            LocalFileSystem.getInstance().refreshAndFindFileByIoFile(new File(file.getPath()));
            project.getBaseDir().refresh(false,true);
        });
    }
}
