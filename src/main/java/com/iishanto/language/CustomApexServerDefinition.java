package com.iishanto.language;

import org.eclipse.lsp4j.InitializeParams;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.ProcessBuilderServerDefinition;
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition;

import java.util.Map;

public class CustomApexServerDefinition extends RawCommandServerDefinition {


    public CustomApexServerDefinition(String ext, String[] command) {
        super(ext, command);
    }

    @Override
    public void customizeInitializeParams(InitializeParams params) {
        params.setRootPath("C:\\Users\\BJIT\\Desktop\\SFDX\\SF_PDF-master");
    }
}
