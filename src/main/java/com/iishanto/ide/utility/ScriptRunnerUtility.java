package com.iishanto.ide.utility;

import com.intellij.notification.NotificationsManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;

import java.io.*;

public class ScriptRunnerUtility {
    //generate singleton
    private static ScriptRunnerUtility instance;
    public static ScriptRunnerUtility getInstance(){
        if(instance==null){
            instance=new ScriptRunnerUtility();
        }
        return instance;
    }
    private ScriptRunnerUtility(){}
    public void runScript(String filePath, Project project){
        try{
            System.out.println("Running script: "+filePath+" in project: "+project.getName());
            FileDocumentManager.getInstance().saveAllDocuments();
            CliExecutor.getInstance().execute(project,"Running script: "+filePath,"apex","run","--file",filePath);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileContent(String filePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
                content.append(line).append("\n");
            } catch (IOException ignored) {
                break;
            }
        }
        return content.toString();
    }
}
