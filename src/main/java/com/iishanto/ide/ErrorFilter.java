package com.iishanto.ide;

import com.intellij.codeInsight.highlighting.HighlightErrorFilter;
import com.intellij.psi.PsiErrorElement;
import org.jetbrains.annotations.NotNull;

public class ErrorFilter extends HighlightErrorFilter {
    @Override
    public boolean shouldHighlightErrorElement(@NotNull PsiErrorElement psiErrorElement) {
        return false;
    }
}
