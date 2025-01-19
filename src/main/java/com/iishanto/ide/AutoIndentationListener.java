package com.iishanto.ide;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.jetbrains.annotations.NotNull;

public class AutoIndentationListener implements DocumentListener {
    private final PsiFile psiFile;
    private final Editor editor;

    public AutoIndentationListener(PsiFile psiFile, Editor editor) {
        this.psiFile = psiFile;
        this.editor = editor;
    }

    @Override
    public void documentChanged(@NotNull DocumentEvent event) {
        WriteCommandAction.runWriteCommandAction(psiFile.getProject(), () -> {
            CodeStyleManager.getInstance(psiFile.getProject()).reformat(psiFile);
        });
    }
}