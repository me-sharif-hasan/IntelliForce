package com.iishanto.ide.ui.runlinemarker;

import com.iishanto.ide.utility.ScriptRunnerUtility;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;

public class ApexScriptRunLineMarkerProvider implements LineMarkerProvider {
    @Override
    @Nullable
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        if (element.getContainingFile() == null ||
                !element.getContainingFile().getName().endsWith(".apex")) {
            return null;
        }

        PsiElement firstElement = element.getContainingFile().getFirstChild();
        while (firstElement != null && firstElement.getText().trim().isEmpty()) {
            firstElement = firstElement.getNextSibling();
        }

        if (!element.equals(firstElement)) {
            return null;
        }

        return new LineMarkerInfo<>(
                element,
                element.getTextRange(),
                AllIcons.RunConfigurations.TestState.Run,
                psiElement -> "Run Apex Script",
                (e, elt) -> {
                    String filePath = elt.getContainingFile().getVirtualFile().getPath();
                    ScriptRunnerUtility.getInstance().runScript(filePath, elt.getProject());
                },  // Fixed empty click handler
                GutterIconRenderer.Alignment.LEFT,
                () -> "Run Apex Script"
        );
    }

    @Override
    public void collectSlowLineMarkers(@NotNull java.util.List<? extends PsiElement> elements,
                                       @NotNull java.util.Collection<? super LineMarkerInfo<?>> result) {
        // Leave empty unless you need slow line markers
    }
}