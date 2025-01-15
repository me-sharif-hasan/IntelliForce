package com.iishanto.server;

import com.iishanto.server.hanlder.LogHandler;
import com.iishanto.server.hanlder.ResponseHandler;
import org.eclipse.lsp4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.ProcessBuilderServerDefinition;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition;

import java.io.IOException;
import java.io.OutputStream;

public class ApexLanguageServerDefinition{
    private OutputStream serverOutputStream;
    private String[] command = new String[]{
            "java",
            "-Xms512m",
            "-Xmx1024m",
            "-jar",
            "-Ddebug.internal.errors=true",
            "-Ddebug.semantic.errors=true",
            "-Ddebug.completion.statistics=true",
            "C:/Users/BJIT/IdeaProjects/IntelliForce/libs/apex-jorje-lsp.jar"
    };
    private ProcessBuilder apexLspProcess;

    private Process process;

    private ApexLanguageServerDefinition(){
        apexLspProcess=new ProcessBuilder(command);
    }

    public void start() throws IOException {
        process = apexLspProcess.start();
        LogHandler logHandler=new LogHandler(process.getErrorStream());
        ResponseHandler responseHandler=new ResponseHandler(process.getInputStream());
        serverOutputStream=process.getOutputStream();
        logHandler.initiate();
        responseHandler.initiate();
    }

    public void submitNotification(String notification) throws IOException {
        String formattedMessage = "Content-Length: " + notification.length() + "\r\n\r\n" + notification;
        serverOutputStream.write(formattedMessage.getBytes());
        serverOutputStream.flush();
    }


    private static ApexLanguageServerDefinition instance;

    public static ApexLanguageServerDefinition getInstance() {
        if(instance==null){
            instance=new ApexLanguageServerDefinition();
        }
        return instance;
    }
}
