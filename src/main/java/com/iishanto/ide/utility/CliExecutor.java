package com.iishanto.ide.utility;

import com.google.common.base.Charsets;
import com.iishanto.ide.config.SalesforceProjectConfig;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CliExecutor {
    private CliExecutor(){}
    private static CliExecutor instance;
    public static CliExecutor getInstance(){
        if(instance==null){
            instance=new CliExecutor();
        }
        return instance;
    }
    public void executeWithOutput(Project project,String title,CliOutputListener outputListener,String ...command) throws ExecutionException {
        System.out.println("Executing command: "+ Arrays.toString(command) +" in project: "+project.getName());
        String sfCliPath = SalesforceProjectConfig.getInstance(project).getSfCliPath();
        String currentAlias = SalesforceProjectConfig.getInstance(project).getSelectedAlias();
        GeneralCommandLine commandLine = new GeneralCommandLine();
        commandLine.setCharset(Charsets.UTF_8);
        commandLine.setExePath(sfCliPath);
        List<String> commandList = new ArrayList<>(Arrays.stream(command).toList());
        commandList.add("-o");
        commandList.add(currentAlias);

        commandLine.addParameters(commandList);
        IDEUtility.SalesforceOutputManager outputManager = IDEUtility.getSalesforceOutputManager(project);
        OSProcessHandler processHandler = getOsProcessHandler(commandLine, outputManager,outputListener);
        outputManager.attachToProcess(processHandler,title);
    }
    public void execute(Project project,String title,String ...command) throws ExecutionException {
        executeWithOutput(project,title,null,command);
    }

    private static @NotNull OSProcessHandler getOsProcessHandler(GeneralCommandLine commandLine, IDEUtility.SalesforceOutputManager outputManager, CliOutputListener outputListener) throws ExecutionException {
        OSProcessHandler processHandler = new OSProcessHandler(commandLine);
        processHandler.addProcessListener(new ProcessListener() {
            String output = "";
            @Override
            public void processTerminated(@NotNull ProcessEvent event) {
                System.out.println("Command executed successfully");
                if(outputListener!=null){
                    outputListener.onOutputAvailable(output);
                }
            }

            @Override
            public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                System.out.println(event.getText());
                output+=event.getText();
            }
        });
        processHandler.startNotify();
        return processHandler;
    }

    public interface CliOutputListener{
        void onOutputAvailable(String output);
    }
}
