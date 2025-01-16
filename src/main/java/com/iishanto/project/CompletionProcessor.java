package com.iishanto.project;

import com.google.gson.JsonObject;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.hanlder.wrappers.CompletionWrapper;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;

import java.util.List;

public class CompletionProcessor implements LspResponseListener {
    private CompletionResultSet completionResultSet;
    private CompletionParameters completionParameters;
    private ProcessingContext processingContext;
    public CompletionProcessor(CompletionResultSet completionResultSet, CompletionParameters completionParameters, ProcessingContext processingContext){
        this.completionResultSet=completionResultSet;
        this.completionParameters=completionParameters;
        this.processingContext=processingContext;
    }

    public void complete(){
        new Thread(()->{
            completionResultSet.addElement(LookupElementBuilder.create("Sample completion item"));

        }).start();
    }
    @Override
    public void listen(JsonObject jsonObject) {
        System.out.println("Completion processed: ");
        System.out.println(jsonObject);
        CompletionWrapper completionWrapper=CompletionWrapper.getFromJsonObject(jsonObject);
        if(!completionWrapper.getResult().getItems().isEmpty()){
            System.out.println("Completion Available");
            List< CompletionWrapper.CompletionItem> items=completionWrapper.getResult().getItems();
            for(CompletionWrapper.CompletionItem completionItem:items){
                System.out.println("Writing completion "+completionItem.getLabel());
                String label=completionItem.getLabel();
                System.out.println("Pushing the log");
                completionResultSet.addElement(LookupElementBuilder.create("Sample completion item"));
                System.out.println("Finished the log");
            }
        }

    }

    @Override
    public String getTargetMethod() {
        return "textDocument/completion";
    }

    @Override
    public boolean isMatching(JsonObject jsonObject) {
        try{
            return jsonObject.get("result").getAsJsonObject().get("isIncomplete").getAsString()!=null;
        }catch (Exception e){
            return false;
        }
    }
}

/*
{
                    "jsonrpc": "2.0",
                    "id": 1,
                    "method": "textDocument/completion",
                    "params": {
                        "textDocument": {
                             "uri": "%s"
                        },
                        "position": {
                            "line": %d,
                            "character": %d
                        },
                        "context": {
                            "triggerKind": 1,
                            "triggerCharacter": "."
                        }
                    }
                }
 */
