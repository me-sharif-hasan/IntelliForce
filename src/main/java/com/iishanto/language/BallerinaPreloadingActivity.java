package com.iishanto.language;

import com.intellij.openapi.application.PreloadingActivity;
import com.intellij.openapi.progress.ProgressIndicator;
import org.wso2.lsp4intellij.IntellijLanguageClient;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition;

public class BallerinaPreloadingActivity extends PreloadingActivity {
    @Override
    public void preload(ProgressIndicator indicator) {
        System.out.println("Preloading");
        String[] command = new String[]{"java", "-Xms512m", "-Xmx1024m", "-jar", "C:\\Users\\Sharif\\IdeaProjects\\ApexJetforce\\libs\\apex-jorje-lsp.jar"};
        RawCommandServerDefinition rcs = new RawCommandServerDefinition("bsl,os", command);
        IntellijLanguageClient.addServerDefinition(rcs);
        System.out.println("loaded");
    }
}