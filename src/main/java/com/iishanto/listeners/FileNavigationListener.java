package com.iishanto.listeners;

import com.iishanto.common.GlobalState;
import com.iishanto.common.Logger;
import com.iishanto.ide.SalesforceProjectStartupActivity;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorEventListener;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class FileNavigationListener implements FileEditorManagerListener, EditorEventListener {
    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        VirtualFile newFile = event.getNewFile();
        if (newFile != null) {
            GlobalState.getInstance().setOpenState(true);
            GlobalState.getInstance().setCurrentFilePath(newFile.getPath());
            DaemonCodeAnalyzer.getInstance(SalesforceProjectStartupActivity.project).restart();
            Logger.log("Navigated to or focused on file: " + newFile.getPath());
        }
    }

    @Override
    public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        System.out.println("File closed: " + file.getPath());
    }


    public static void registerListener() {
        FileEditorManager.getInstance(SalesforceProjectStartupActivity.project).addFileEditorManagerListener(new FileNavigationListener());
    }
}
