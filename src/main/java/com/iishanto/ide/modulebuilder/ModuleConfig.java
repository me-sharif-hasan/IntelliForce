package com.iishanto.ide.modulebuilder;

import com.iishanto.ide.config.SalesforceProjectConfig;
import com.intellij.openapi.project.Project;

public class ModuleConfig {
    private ModuleConfig(){}
    public static String sfCliPath;
    public static String projectName;
    public static String moduleFilePath;
    public static String contentEntryPath;


    public static String getSfCliPath(Project project) {
        SalesforceProjectConfig config = SalesforceProjectConfig.getInstance(project);
        return config.getSfCliPath();
    }

    public static void setSfCliPath(Project project,String sfCliPath){
        SalesforceProjectConfig config = SalesforceProjectConfig.getInstance(project);
        config.setSfCliPath(sfCliPath);
    }



    public static void printDetails(){
        System.out.printf("CLI PATH: %s\nProject Name: %s\nModule File Path:%s\nContent Entry Path:%s\n",sfCliPath,projectName,moduleFilePath,contentEntryPath);
    }
}
