package com.iishanto.ide;

import com.iishanto.common.Logger;
import com.iishanto.listeners.ApexDiagnosticCallbackListener;
import com.iishanto.listeners.AutoCompletionCallbackListener;
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
    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet result) {
        try {
            Editor editor = parameters.getEditor();
            int offset = parameters.getOffset();
            Position serverPos = DocumentUtils.offsetToLSPPos(editor, offset);
            NotificationHub.getInstance().didOpen(
                    editor.getVirtualFile().getPath(),
                    editor.getDocument().getText(),
                    new ApexDiagnosticCallbackListener(
                            editor.getVirtualFile().getPath(),
                            editor.getDocument().getText()
                    )
                            .shouldAnnotate(false)
            );
            List<LookupElementBuilder> lookupElementBuilderList = AutoCompletionCallbackListener.getAutoCompleteSuggestions(serverPos.getLine(), serverPos.getCharacter(), editor.getVirtualFile().getPath());
            result.addAllElements(lookupElementBuilderList);
        } catch (ProcessCanceledException e) {
            Logger.log("Process canceled: " + e.getMessage());
        } catch (Exception e) {
            Logger.log("LSP Completions ended with an error: " + e.getLocalizedMessage());
        }
    }
}
