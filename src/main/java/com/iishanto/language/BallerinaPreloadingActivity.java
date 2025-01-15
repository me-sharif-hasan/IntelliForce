package com.iishanto.language;

import com.intellij.openapi.application.PreloadingActivity;
import com.intellij.openapi.progress.ProgressIndicator;
import org.wso2.lsp4intellij.IntellijLanguageClient;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition;

public class BallerinaPreloadingActivity extends PreloadingActivity {
    @Override
    public void preload(ProgressIndicator indicator) {
        System.out.println("Preloading");
        String[] command = new String[]{
                "java",
                "-Xms512m",
                "-Xmx1024m",
                "-jar",
                "-Ddebug.internal.errors=true",
                "-Ddebug.semantic.errors=true",
                "-Ddebug.completion.statistics=true",
                "C:/Users/BJIT/IdeaProjects/IntelliForce/libs/apex-jorje-lsp.jar"
        };
        CustomApexServerDefinition rcs = new CustomApexServerDefinition("cls", command);
        IntellijLanguageClient.addServerDefinition(rcs);
        System.out.println("loaded");
    }
}