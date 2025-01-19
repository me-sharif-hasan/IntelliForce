package com.iishanto.ide;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class AutoCompleteTypedHandler extends TypedHandlerDelegate {
    @Override
    public Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
        // Handle auto-completion logic
        if (c == '(') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), ")");
        } else if (c == '[') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), "]");
        } else if (c == '{') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), "}");
        } else if (c == '\'') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), "'");
        } else if (c == '"') {
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), "\"");
        }
        return Result.CONTINUE;
    }
}
