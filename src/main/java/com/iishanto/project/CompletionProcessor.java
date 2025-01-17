package com.iishanto.project;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.hanlder.wrappers.CompletionWrapper;
import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class CompletionProcessor implements LspResponseListener {
    //    private final CompletionResultSet completionResultSet;
    private CompletionParameters completionParameters;
    private ProcessingContext processingContext;
    private final CompletionContributor completionContributor;

    public CompletionProcessor(
            CompletionContributor completionContributor,
//            CompletionResultSet completionResultSet,
            CompletionParameters completionParameters,
            ProcessingContext processingContext
    ) {
        this.completionContributor = completionContributor;
//        this.completionResultSet=completionResultSet;
        this.completionParameters = completionParameters;
        this.processingContext = processingContext;
    }

    static int id=0;

    @Override
    synchronized public void listen(JsonObject jsonObject) {



//        System.out.printf("Completion processed: %d\n", id);
//        System.out.println(jsonObject);
//        System.out.println("\n\n---------------------"+jsonObject.get("result").getAsJsonObject().get("items").getAsJsonArray().isEmpty()+" "+(id)+"--------------------\n\n");
//        if(!jsonObject.get("result").getAsJsonObject().get("items").getAsJsonArray().isEmpty()){
//            JsonArray items = jsonObject.get("result").getAsJsonObject().get("items").getAsJsonArray();
//            System.out.println("Success doing completion ->>>>>>>");
//
//            completionContributor.extend(CompletionType.SMART, PlatformPatterns.psiElement(), new CompletionProvider<>() {
//                @Override
//                protected void addCompletions(@NotNull CompletionParameters completionParameters, @NotNull ProcessingContext processingContext, @NotNull CompletionResultSet completionResultSet) {
//                    System.out.println("Doing completion "+(id++));
//                    for(JsonElement item : items){
//                        String label = item.getAsJsonObject().get("label").getAsString();
//                        String detail= item.getAsJsonObject().get("detail").getAsString();
//                        System.out.println("label: "+id+" " + label+" detail: " + detail);
//                        completionResultSet.addElement(LookupElementBuilder.create(label));
//                    }
//                }
//            });
//
//            ApplicationManager.getApplication().invokeLater(() -> {
//                AutoPopupController.getInstance(completionParameters.getEditor().getProject())
//                        .scheduleAutoPopup(completionParameters.getEditor());
//            });
//        }
//
//        id++;

//        CompletionWrapper completionWrapper = CompletionWrapper.getFromJsonObject(jsonObject);
//        if (!completionWrapper.getResult().getItems().isEmpty()) {
//            System.out.println("Completion Available");
//            List<CompletionWrapper.CompletionItem> items = completionWrapper.getResult().getItems();
//            items.sort(Comparator.comparing(CompletionWrapper.CompletionItem::getSortText));
//            completionContributor.extend(CompletionType.BASIC, PlatformPatterns.psiElement(), new CompletionProvider<>() {
//                @Override
//                protected void addCompletions(@NotNull CompletionParameters completionParameters, @NotNull ProcessingContext processingContext, @NotNull CompletionResultSet completionResultSet) {
//                    for (CompletionWrapper.CompletionItem completionItem : items) {
//                        System.out.println("Adding: "+completionItem.getDetail());
//                        String label = completionItem.getLabel();
//                        completionResultSet.addElement(LookupElementBuilder.create(label));
//                    }
//                }
//            });
//        }

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