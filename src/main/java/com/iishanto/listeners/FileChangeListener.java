package com.iishanto.listeners;

import com.iishanto.common.GlobalState;
import com.iishanto.common.Logger;
import com.iishanto.ide.SalesforceProjectStartupActivity;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;

import java.io.IOException;

public class FileChangeListener implements VirtualFileListener {

    @Override
    public void contentsChanged(VirtualFileEvent event) {
        System.out.println("File changed: " + event.getFile().getPath());
    }

    @Override
    public void fileCreated(VirtualFileEvent event) {
        System.out.println("File created: " + event.getFile().getPath());
        try {
            String content = new String(event.getFile().contentsToByteArray());
            NotificationHub.getInstance().didOpen(event.getFile().getPath(), content, new ApexDocumentDiagnosticReportCallbackListener());
            NotificationHub.getInstance().didChange(event.getFile().getPath(), content, new ApexDocumentDiagnosticReportCallbackListener());
            NotificationHub.getInstance().didSave(event.getFile().getPath(), content, new ApexDocumentDiagnosticReportCallbackListener());
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
    }

    @Override
    public void fileDeleted(VirtualFileEvent event) {
        System.out.println("File deleted: " + event.getFile().getPath());
    }


    @SuppressWarnings("deprecation")
    public static void registerListener() {
        VirtualFileManager.getInstance().addVirtualFileListener(new FileChangeListener());
    }
}
