package com.iishanto.ide.codeformatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.DocumentBasedFormattingModel;
import com.intellij.psi.formatter.PsiBasedFormattingModel;
import org.jetbrains.annotations.NotNull;

public class ApexLanguageFormattingModelBuilder implements FormattingModelBuilder {
    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        ASTNode rootNode = formattingContext.getNode();
        CodeStyleSettings settings = formattingContext.getCodeStyleSettings();
        PsiFile file = formattingContext.getContainingFile();
        ApexBlockFormatter rootBlock = new ApexBlockFormatter(rootNode, Wrap.createWrap(WrapType.NONE, false), null);
        return FormattingModelProvider.createFormattingModelForPsiFile(file,rootBlock,settings);
    }
}