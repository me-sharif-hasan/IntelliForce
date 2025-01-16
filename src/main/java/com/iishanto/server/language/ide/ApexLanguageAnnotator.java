package com.iishanto.server.language.ide;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.hanlder.wrappers.DiagnosticsResult;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ApexLanguageAnnotator implements Annotator {
    public ApexLanguageAnnotator(){
        System.out.println("Loaded - iishanto");
    }
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        if(psiElement.getClass().getName()!=null){
            if(psiElement.getClass().getName().contains("ApexClassFile")){
                try {
                    handleClassAnnotation(psiElement,annotationHolder);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void handleClassAnnotation(@NotNull PsiElement psiClass, @NotNull AnnotationHolder annotationHolder) throws IOException {
        System.out.println("iishanto - annot "+psiClass.getText()+" "+psiClass.getClass().getName());
        String filePath = psiClass.getContainingFile().getVirtualFile().getPath();
        System.out.println("iipath "+filePath);

        NotificationHub.getInstance().didOpen(filePath,psiClass.getText(),new LspResponseListener(){

            @Override
            public void listen(JsonObject jsonObject) {
                System.out.println("'annot-iishant': "+jsonObject);
                Type diagnosticsResultListType = new TypeToken<List<DiagnosticsResult>>(){}.getType();

                List<DiagnosticsResult> diagnosticsResults = new Gson().fromJson(jsonObject.get("params").getAsJsonObject().get("diagnostics"), diagnosticsResultListType);
                String []lines = psiClass.getText().split("\n");
                for (DiagnosticsResult diagnosticsResult : diagnosticsResults) {
                    System.out.println(diagnosticsResult.getMessage());

                    int begin=0;
                    int end=0;
                    System.out.println("OKK "+diagnosticsResult.getRange().getStart().getLine()+" "+diagnosticsResult.getRange().getEnd().getLine());
                    for(int i=0;i<lines.length;i++){
                        System.out.println("iiline: "+lines[i].length());
                        if(diagnosticsResult.getRange().getStart().getLine()>i){
                            begin+=lines[i].length()+1;
                        }else if(diagnosticsResult.getRange().getStart().getLine()==i){
                            begin+=diagnosticsResult.getRange().getStart().getCharacter();
                            System.out.println(begin+";"+diagnosticsResult.getRange().getEnd().getCharacter());
                        }

                        if(diagnosticsResult.getRange().getEnd().getLine()>i){
                            end+=lines[i].length()+1;
                        }else if(diagnosticsResult.getRange().getEnd().getLine()==i){
                            end+=diagnosticsResult.getRange().getEnd().getCharacter();
                        }
                    }
                        System.out.println("Begin: "+begin+" End: "+end);
                    if(begin<=end&&end<=psiClass.getText().length()){
                        TextRange textRange=new TextRange(begin,end);;
                        annotationHolder.newAnnotation(HighlightSeverity.ERROR,diagnosticsResult.getMessage())
                                .range(textRange)
                                .create();
                    }
                }
            }

            @Override
            public String getTargetMethod() {
                return "textDocument/publishDiagnostics";
            }
        });
    }
}
