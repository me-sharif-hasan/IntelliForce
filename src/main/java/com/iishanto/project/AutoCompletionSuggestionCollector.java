package com.iishanto.project;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.iishanto.server.hanlder.AsynchronousSessionedTask;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.codeInsight.lookup.LookupElementBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutoCompletionSuggestionCollector implements LspResponseListener {
    public static List<LookupElementBuilder> suggestionElement=new ArrayList<>();
    public List<LookupElementBuilder> suggestions=new ArrayList<>();
    public boolean isDone=false;
    public int sessionId=0;
    AsynchronousSessionedTask sessionTask;
    public AutoCompletionSuggestionCollector(int sessionId, AsynchronousSessionedTask asyncTask) {
        this.sessionId=sessionId;
        this.sessionTask=asyncTask;
    }
    private AutoCompletionSuggestionCollector(){}
    @Override
    synchronized public void listen(JsonObject jsonObject) {
        try{
//            System.out.printf("Completion processed:\n");
//        System.out.println(jsonObject);
            System.out.println("\n\n---------------------"+jsonObject.get("result").getAsJsonObject().get("items").getAsJsonArray().isEmpty()+"--------------------\n\n");
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
                        System.out.println(item);
                        e.printStackTrace();
                    }
                    if(sessionTask!=null) sessionTask.completeTask(sessionId, localLookupElementBuilders);
                }
//            ApplicationManager.getApplication().invokeLater(()->{
//                AutoPopupController.getInstance(SalesforceProjectStartupActivity.project).scheduleAutoPopup(editor);
//            });
            }
        }finally {
            isDone=true;
        }
    }

    public static List<LookupElementBuilder> getAutoCompleteSuggestions(int lineNumber,int columnNumber,String filePath) throws IOException {
        AutoCompletionSuggestionCollector autoCompletionSuggestionCollector=new AutoCompletionSuggestionCollector();
        NotificationHub.getInstance()
                .completion(filePath,lineNumber,columnNumber,autoCompletionSuggestionCollector);
        while (!autoCompletionSuggestionCollector.isDone){
            System.out.print("*");
            //waiting
        }
        return autoCompletionSuggestionCollector.suggestions;
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
