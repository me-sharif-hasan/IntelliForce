package com.iishanto.listeners;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.hanlder.wrappers.DiagnosticsResult;
import com.intellij.lang.annotation.AnnotationHolder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ApexDocumentDiagnosticReportCallbackListener extends LspResponseListener {
    @Override
    public boolean isCompletableFuture() {
        return true;
    }

    @Override
    public String getTargetMethod() {
        return "textDocument/publishDiagnostics";
    }

    public List<DiagnosticsResult> getDiagnosisResults() throws ExecutionException, InterruptedException {
        JsonObject jsonObject = this.get();
        Type diagnosticsResultListType = new TypeToken<List<DiagnosticsResult>>() {
        }.getType();
        return new Gson().fromJson(jsonObject.get("params").getAsJsonObject().get("diagnostics"), diagnosticsResultListType);
    }

}
