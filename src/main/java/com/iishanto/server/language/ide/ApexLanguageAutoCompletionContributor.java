package com.iishanto.server.language.ide;

import com.iishanto.apex.psi.ApexClassBody;
import com.iishanto.apex.psi.ApexClassFile;
import com.iishanto.project.CompletionProcessor;
import com.iishanto.server.notification.NotificationHub;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ApexLanguageAutoCompletionContributor extends CompletionContributor {
    public ApexLanguageAutoCompletionContributor(){
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters completionParameters, @NotNull ProcessingContext processingContext, @NotNull CompletionResultSet completionResultSet) {
                int offset = completionParameters.getOffset();
                Document document = completionParameters.getEditor().getDocument();
                int lineNumber = document.getLineNumber(offset);
                int lineStartOffset = document.getLineStartOffset(lineNumber);
                int columnNumber = offset - lineStartOffset;
                String path = completionParameters.getOriginalFile().getVirtualFile().getPath();
                System.out.println("Auto completion working: " + lineNumber + "; " + columnNumber + " " + path);
                try {
                    NotificationHub.getInstance().completion(
                            path,
                            lineNumber,
                            columnNumber,
                            new CompletionProcessor(
                                    ApexLanguageAutoCompletionContributor.this,
//                                    completionResultSet,
                                    completionParameters,
                                    processingContext
                            )
                    );
                } catch (IOException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        });
    }
}
