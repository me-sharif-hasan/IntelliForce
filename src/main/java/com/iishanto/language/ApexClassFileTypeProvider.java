package com.iishanto.language;

import com.iishanto.apex.filetypes.ApexClassFileType;
import com.intellij.openapi.progress.util.AbstractProgressIndicatorBase;
import org.wso2.lsp4intellij.IntellijLanguageClient;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition;

public class ApexClassFileTypeProvider extends ApexClassFileType {
    public static ApexClassFileType INSTANCE = new ApexClassFileTypeProvider();
    public ApexClassFileTypeProvider() {
        new BallerinaPreloadingActivity().preload();
    }
}
