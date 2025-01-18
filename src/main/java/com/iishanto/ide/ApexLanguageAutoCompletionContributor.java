package com.iishanto.ide;

import com.iishanto.project.AnnotationProcessor;
import com.iishanto.project.AutoCompletionSuggestionCollector;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.progress.ProcessCanceledException;
import org.eclipse.lsp4j.Position;
import org.jetbrains.annotations.NotNull;
import org.wso2.lsp4intellij.utils.DocumentUtils;

import java.util.List;

public class ApexLanguageAutoCompletionContributor extends CompletionContributor {
    private static int sessionId = 0;
    public static List<LookupElementBuilder> lookupElementBuilders;
    public static boolean ideAutoCompleteFlag = false;
    public static boolean manualAutoCompleteFlag = false;

    public ApexLanguageAutoCompletionContributor() {
//        System.out.println("Auto completion contributor invoked");
//        System.out.println("Available suggestions");
//        extend(CompletionType.BASIC,PlatformPatterns.psiElement(),new CompletionProvider<CompletionParameters>() {
//            @Override
//            protected void addCompletions(@NotNull CompletionParameters completionParameters, @NotNull ProcessingContext processingContext, @NotNull CompletionResultSet completionResultSet) {
//                System.out.println("Auto completion provider called "+completionParameters.getInvocationCount());
//                if(!ideAutoCompleteFlag){
//                    ideAutoCompleteFlag=true;
//                    if(lookupElementBuilders!=null){
//                        lookupElementBuilders.clear();
//                    }
//                    return;
//                }
//                ideAutoCompleteFlag=false;
//                if(lookupElementBuilders!=null){
//                    for(LookupElementBuilder lookupElementBuilder:lookupElementBuilders){
//                        completionResultSet.addElement(lookupElementBuilder);
//                    }
//                    lookupElementBuilders.clear();
//                }
//            }
//        });
    }

    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet result) {
        System.out.println("Filling completion variants");
        try {
            Editor editor = parameters.getEditor();
            int offset = parameters.getOffset();
            Position serverPos = DocumentUtils.offsetToLSPPos(editor, offset);
            NotificationHub.getInstance().didOpen(
                    editor.getVirtualFile().getPath(),
                    editor.getDocument().getText(),
                    new AnnotationProcessor(
                            editor.getVirtualFile().getPath(),
                            editor.getDocument().getText()
                    )
                            .shouldAnnotate(false)
            );
            List<LookupElementBuilder> lookupElementBuilderList = AutoCompletionSuggestionCollector.getAutoCompleteSuggestions(serverPos.getLine(), serverPos.getCharacter(), editor.getVirtualFile().getPath());
            for (LookupElementBuilder lookupElementBuilder : lookupElementBuilderList) {
                System.out.println("Adding lookup element: " + lookupElementBuilder.getLookupString());
            }
            result.addAllElements(lookupElementBuilderList);
        } catch (ProcessCanceledException ignored) {
            // ProcessCanceledException can be ignored.
            System.out.println("Process canceled: " + ignored.getMessage());
        } catch (Exception e) {
            System.out.println("LSP Completions ended with an error: " + e.getLocalizedMessage());
        }
    }
}
