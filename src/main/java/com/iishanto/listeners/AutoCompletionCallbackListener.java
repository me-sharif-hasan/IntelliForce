package com.iishanto.listeners;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.iishanto.common.Logger;
import com.iishanto.server.hanlder.AsynchronousSessionedTask;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.codeInsight.lookup.LookupElementBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutoCompletionCallbackListener implements LspResponseListener {
    public static List<LookupElementBuilder> suggestionElement=new ArrayList<>();
    public List<LookupElementBuilder> suggestions=new ArrayList<>();
    public boolean isDone=false;
    public int sessionId=0;
    AsynchronousSessionedTask sessionTask;
    private AutoCompletionCallbackListener(){}
    @Override
    synchronized public void listen(JsonObject jsonObject) {
        try{
            if(!jsonObject.get("result").getAsJsonObject().get("items").getAsJsonArray().isEmpty()){
                JsonArray items = jsonObject.get("result").getAsJsonObject().get("items").getAsJsonArray();
                if(!items.isEmpty()){
                    suggestionElement.clear();
                }
                for(JsonElement item : items){
                    List<LookupElementBuilder> localLookupElementBuilders=new ArrayList<>();
                    try{
                        String label = item.getAsJsonObject().get("label").getAsString();
                        System.out.println("Suggestion "+label);
                        LookupElementBuilder lookupElementBuilder= LookupElementBuilder.create(label);
                        suggestionElement.add(lookupElementBuilder);
                        localLookupElementBuilders.add(lookupElementBuilder);
                        suggestions.add(lookupElementBuilder);
                    }catch (Throwable e){
                        Logger.log(e);
                    }
                    if(sessionTask!=null) sessionTask.completeTask(sessionId, localLookupElementBuilders);
                }
            }
        }finally {
            isDone=true;
        }
    }

    public static List<LookupElementBuilder> getAutoCompleteSuggestions(int lineNumber,int columnNumber,String filePath) throws IOException {
        AutoCompletionCallbackListener autoCompletionCallbackListener =new AutoCompletionCallbackListener();
        NotificationHub.getInstance()
                .completion(filePath,lineNumber,columnNumber, autoCompletionCallbackListener);
        while (!autoCompletionCallbackListener.isDone){
            System.out.print("*");
            //waiting
        }
        return autoCompletionCallbackListener.suggestions;
    }

    @Override
    public String getTargetMethod() {
        return "textDocument/completion";
    }

    @Override
    public boolean isMatching(JsonObject jsonObject) {
        try {
            return jsonObject.get("result").getAsJsonObject().get("isIncomplete").getAsString() != null;
        } catch (Exception e) {
            return false;
        }
    }
}
