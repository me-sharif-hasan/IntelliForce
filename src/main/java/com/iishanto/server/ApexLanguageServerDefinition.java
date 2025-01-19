package com.iishanto.server;

import com.iishanto.server.hanlder.LogHandler;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.hanlder.ResponseHandler;
import com.intellij.openapi.util.io.FileUtil;
import org.eclipse.lsp4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.ProcessBuilderServerDefinition;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class ApexLanguageServerDefinition{
    private OutputStream serverOutputStream;
    private String[] command;
    private final ProcessBuilder apexLspProcess;

    private Process process;

    private ApexLanguageServerDefinition() throws IOException {
        apexLspProcess=ServerExecutableManager.runServer();
        if(apexLspProcess==null){
            throw new IOException("Failed to start server");
        }
    }

    public void start(Map<String, LspResponseListener> listenerRegistry) throws IOException {
        process = apexLspProcess.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (process != null && process.isAlive()) {
                process.destroy();
                System.out.println("Server process terminated.");
            }
        }));

        LogHandler logHandler=new LogHandler(process.getErrorStream());
        ResponseHandler responseHandler=new ResponseHandler(process.getInputStream(),process.getOutputStream());
        serverOutputStream=process.getOutputStream();
        logHandler.initiate();
        responseHandler.initiate(listenerRegistry);
    }

    public void submitNotification(String notification) throws IOException {
        System.out.println("Sending "+notification);
        String formattedMessage = "Content-Length: " + (notification.length()) + "\r\n\r\n" + notification;
        serverOutputStream.write(formattedMessage.getBytes());
        serverOutputStream.flush();
        serverOutputStream.flush();
        serverOutputStream.flush();
    }


    private static ApexLanguageServerDefinition instance;

    public static ApexLanguageServerDefinition getInstance() throws IOException {
        if(instance==null){
            instance=new ApexLanguageServerDefinition();
        }
        return instance;
    }
}
