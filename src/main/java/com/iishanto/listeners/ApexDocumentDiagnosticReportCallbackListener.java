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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ApexDocumentDiagnosticReportCallbackListener extends LspResponseListener {
    @Override
    public boolean isCompletableFuture() {
        return true;
    }

    @Override
    public String getTargetMethod() {
        return "textDocument/publishDiagnostics";
    }
    ReentrantLock lock = new ReentrantLock();
    synchronized public List<DiagnosticsResult> getDiagnosisResults() {
        try{
            lock.lock();
            System.out.println("Getting diagnosis results");
            JsonObject jsonObject = this.get(3, TimeUnit.SECONDS);
            Type diagnosticsResultListType = new TypeToken<List<DiagnosticsResult>>() {
            }.getType();
            return new Gson().fromJson(jsonObject.get("params").getAsJsonObject().get("diagnostics"), diagnosticsResultListType);
        }catch (Exception e){
            return null;
        }finally {
            lock.unlock();
        }
    }

}
