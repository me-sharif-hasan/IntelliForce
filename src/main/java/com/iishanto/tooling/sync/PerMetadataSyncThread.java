package com.iishanto.tooling.sync;

import com.iishanto.listeners.ApexDocumentDiagnosticReportCallbackListener;
import com.iishanto.server.notification.NotificationHub;
import com.iishanto.tooling.network.ToolingApiHttpClient;
import com.iishanto.tooling.network.response.SObjectDescribeResponse;
import com.iishanto.tooling.network.response.SObjectsListResponse;
import com.intellij.openapi.project.Project;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.CompletableFuture;

public class PerMetadataSyncThread extends Thread{
    private final String sObjectUrl;
    private final String sObjectMetadataName;
    private final Project project;
    public PerMetadataSyncThread(Project project,String sObjectUrl, String sObjectMetadataName){
        this.sObjectUrl=sObjectUrl;
        this.project=project;
        this.sObjectMetadataName=sObjectMetadataName;
    }

    @Override
    public synchronized void run() {
        System.out.println("Syncing object: "+sObjectMetadataName);
        ToolingApiHttpClient toolingApiHttpClient=ToolingApiHttpClient.getInstance();
        CompletableFuture<String> sObjectMetadata=new CompletableFuture<>();
        toolingApiHttpClient.get(sObjectUrl,sObjectMetadata);
        String sObjectMetadataResponse=sObjectMetadata.join();
        SObjectDescribeResponse sObjectDescribeResponse = SObjectDescribeResponse.fromJson(sObjectMetadataResponse,SObjectDescribeResponse.class);
        String classBody=SObjectDescribeToApexClassConverterWorker.createApexClass(sObjectDescribeResponse);

        String projectRootPath = project.getBasePath();
        File file = new File(projectRootPath+"/.sfdx/tools/sobjects/standardObjects/" + sObjectMetadataName + ".cls");
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(classBody);
            fileWriter.close();
            NotificationHub.getInstance().didChange(file.getAbsolutePath().replaceAll("\\\\","/"),classBody,new ApexDocumentDiagnosticReportCallbackListener());
            System.out.println("File written: "+file.getAbsolutePath()+" "+classBody.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
