package com.iishanto.tooling.sync;

import com.iishanto.tooling.SalesforceToolingApi;
import com.intellij.openapi.project.Project;

public class ObjectSynchronizer {
    private static ObjectSynchronizer instance;
    public static ObjectSynchronizer getInstance(){
        if(instance==null){
            instance=new ObjectSynchronizer();
        }
        return instance;
    }
    private ObjectSynchronizer(){}

    public void sync(Project project){
        new Thread(()->{
            try{
                System.out.println("Syncing objects");
                SalesforceToolingApi toolingApi = SalesforceToolingApi. getInstance(project);
                toolingApi.getStandardObjectList();
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
