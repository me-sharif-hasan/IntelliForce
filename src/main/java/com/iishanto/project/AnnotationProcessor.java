package com.iishanto.project;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.hanlder.wrappers.DiagnosticsResult;
import com.intellij.codeInsight.AutoPopupController;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.util.TextRange;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationProcessor implements LspResponseListener {
    private final String fileUrl;
    private final String content;
    private AnnotationHolder annotationHolder;
    private static final Map<String, AnnotationHolder> registry = new HashMap<>();
    Editor editor;

    public AnnotationProcessor(String fileLocation, String content) {
        this.fileUrl = fileLocation;
        this.content = content;
        editor = FileEditorManager.getInstance(SalesforceProjectStartupActivity.project).getSelectedTextEditor();
    }

    public AnnotationProcessor setAnnotationHolder(AnnotationHolder holder) {
        this.annotationHolder = holder;
        registry.put(fileUrl, holder);
        return this;
    }

    private void invokeAutoComplete(){
        System.out.println("trying completion");
        Editor editor = FileEditorManager.getInstance(SalesforceProjectStartupActivity.project).getSelectedTextEditor();
        if (editor == null) {
            System.out.println("No active editor found.");
            return;
        }

        // Get the document and caret model
        Document document = editor.getDocument();
        CaretModel caretModel = editor.getCaretModel();

        // Get the offset of the caret
        int offset = caretModel.getOffset();

        // Convert offset to line and column
        int line = document.getLineNumber(offset);
        int lineStartOffset = document.getLineStartOffset(line);
        int character = offset - lineStartOffset;

        // Output the cursor position
        System.out.println("Current line: " + (line + 1)); // Line numbers are 0-based
        System.out.println("Current character: " + (character + 1)); // Column numbers are 0-based

//        ApplicationManager.getApplication().invokeLater(()->{
//            ApplicationManager.getApplication().runReadAction(()->{
//                System.out.println("invoking auto complete");
//                AutoPopupController.getInstance(SalesforceProjectStartupActivity.project).scheduleAutoPopup(editor);
//            });
//        });
    }

    @Override
    public void listen(JsonObject jsonObject) {
        try {
            invokeAutoComplete();
            Type diagnosticsResultListType = new TypeToken<List<DiagnosticsResult>>() {
            }.getType();
            List<DiagnosticsResult> diagnosticsResults = new Gson().fromJson(jsonObject.get("params").getAsJsonObject().get("diagnostics"), diagnosticsResultListType);
            String[] lines = content.split("\n");
            for (DiagnosticsResult diagnosticsResult : diagnosticsResults) {
                System.out.println(diagnosticsResult.getMessage());

                int begin = 0;
                int end = 0;
                for (int i = 0; i < lines.length; i++) {
                    if (diagnosticsResult.getRange().getStart().getLine() > i) {
                        begin += lines[i].length() + 1;
                    } else if (diagnosticsResult.getRange().getStart().getLine() == i) {
                        begin += diagnosticsResult.getRange().getStart().getCharacter();
                    }

                    if (diagnosticsResult.getRange().getEnd().getLine() > i) {
                        end += lines[i].length() + 1;
                    } else if (diagnosticsResult.getRange().getEnd().getLine() == i) {
                        end += diagnosticsResult.getRange().getEnd().getCharacter();
                    }
                }
                if (begin <= end && end <= content.length()) {
                    TextRange textRange = new TextRange(begin, end);
                    AnnotationHolder currentHolder = annotationHolder;
                    if (annotationHolder == null && registry.containsKey(fileUrl)) {
                        currentHolder = registry.get(fileUrl);
                    }
                    if (currentHolder != null) {
                        try{
                            currentHolder.newAnnotation(HighlightSeverity.ERROR, diagnosticsResult.getMessage())
                                    .range(textRange)
                                    .create();
                        }catch (Exception ignored){}
                    } else {
                        System.out.println("Current holder is null");
                    }
                }
            }
        } catch (Throwable e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public String getTargetMethod() {
        return "textDocument/publishDiagnostics";
    }
}
