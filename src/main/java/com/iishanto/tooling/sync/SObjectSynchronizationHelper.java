package com.iishanto.tooling.sync;

import com.iishanto.tooling.network.response.SObjectMetadata;
import com.intellij.openapi.project.Project;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SObjectSynchronizationHelper {
    private static final ExecutorService executorService= Executors.newFixedThreadPool(10);
    public static void startSync(Project project,List<SObjectMetadata> sObjectMetadataList){
        System.out.println("Syncing objects");
        for(SObjectMetadata sObjectMetadata:sObjectMetadataList){
            String sObjectUrl=sObjectMetadata.getUrls().getDescribe();
            String sObjectMetadataName=sObjectMetadata.getName();
            executorService.submit(new PerMetadataSyncThread(project,sObjectUrl,sObjectMetadataName));
        }
    }
}
