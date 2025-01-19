package com.iishanto.server.hanlder;

import com.google.gson.JsonObject;

import java.util.concurrent.CompletableFuture;

public abstract class LspResponseListener extends CompletableFuture<JsonObject> {
    public void listen(JsonObject jsonObject){}
    public String getTargetMethod(){
        return null;
    }
    public boolean isMatching(JsonObject jsonObject){
        return false;
    }
    public boolean isCompletableFuture(){
        return false;
    }
}
