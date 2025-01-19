package com.iishanto.listeners;

import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;

public class FileChangeListener implements VirtualFileListener {

    @Override
    public void contentsChanged(VirtualFileEvent event) {
        System.out.println("File changed: " + event.getFile().getPath());
    }

    @Override
    public void fileCreated(VirtualFileEvent event) {
        System.out.println("File created: " + event.getFile().getPath());
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
