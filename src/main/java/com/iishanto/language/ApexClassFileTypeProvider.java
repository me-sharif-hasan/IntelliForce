package com.iishanto.language;

import com.iishanto.apex.filetypes.ApexClassFileType;
import com.iishanto.server.ApexLanguageServerDefinition;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.openapi.progress.util.AbstractProgressIndicatorBase;
import org.wso2.lsp4intellij.IntellijLanguageClient;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition;

public class ApexClassFileTypeProvider extends ApexClassFileType {
    public static ApexClassFileType INSTANCE = new ApexClassFileTypeProvider();

    public ApexClassFileTypeProvider(){
        try{
//            NotificationHub.getInstance().initialize();
            AbstractProgressIndicatorBase apib=new AbstractProgressIndicatorBase();
            apib.start();
            new BallerinaPreloadingActivity().preload(apib);
        }catch (Exception e){
            System.out.println("error - iishanto"+e.getLocalizedMessage());
        }
    }
}
