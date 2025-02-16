package com.iishanto.ide.codeformatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class ApexLanguageFormattingModelBuilder implements FormattingModelBuilder {
    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        ASTNode rootNode = formattingContext.getNode();
        CodeStyleSettings settings = formattingContext.getCodeStyleSettings();
        System.out.println("got settings: "+settings.getIndentOptions().INDENT_SIZE);
        // Create the root block for formatting
        ApexLanguageBlock rootBlock = new ApexLanguageBlock(rootNode, null, Indent.getNoneIndent(), settings);
        return FormattingModelProvider.createFormattingModelForPsiFile(
                formattingContext.getContainingFile(),
                rootBlock,
                settings
        );
    }

}
