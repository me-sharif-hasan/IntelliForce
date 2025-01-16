package com.iishanto.server.hanlder;

import com.google.gson.JsonObject;

public interface LspResponseListener {
    void listen(JsonObject jsonObject);
    String getTargetMethod();
    default boolean isMatching(JsonObject jsonObject){
        return false;
    }
}
