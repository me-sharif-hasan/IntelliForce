package com.iishanto.common;

import com.iishanto.server.notification.NotificationHub;

import java.io.IOException;

public class Configs {
    private Configs(){}

    private static Configs instance;
    public static Configs getInstance(){
        if(instance == null){
            instance = new Configs();
        }
        return instance;
    }

    private String projectRoot;

    public void setProjectRoot(String projectRoot) throws IOException {
        this.projectRoot = projectRoot;
        NotificationHub.getInstance().initialize();
    }


    public String getProjectRoot() {
        return projectRoot;
    }
}
