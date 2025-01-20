package com.iishanto.ide.modulebuilder;

public class ModuleConfig {
    private ModuleConfig(){}
    public static String sfCliPath;
    public static String projectName;
    public static String moduleFilePath;
    public static String contentEntryPath;

    public static void printDetails(){
        System.out.printf("CLI PATH: %s\nProject Name: %s\nModule File Path:%s\nContent Entry Path:%s\n",sfCliPath,projectName,moduleFilePath,contentEntryPath);
    }
}
