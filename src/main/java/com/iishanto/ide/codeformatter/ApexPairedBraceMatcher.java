package com.iishanto.ide.codeformatter;

import com.iishanto.language.sf.apex.psi.ApexTypes;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApexPairedBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = new BracePair[]{
            new BracePair(ApexTypes.LBRACE, ApexTypes.RBRACE, true), // { }
            new BracePair(ApexTypes.LPAREN, ApexTypes.RPAREN, false), // ( )
            new BracePair(ApexTypes.LBRACKET, ApexTypes.RBRACKET, false) // [ ]
    };

    @Override
    public BracePair @NotNull [] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        // Allow braces before most tokens, except specific cases like identifiers or keywords
        return contextType != ApexTypes.IDENTIFIER &&
                contextType != ApexTypes.SEMICOLON &&
                contextType != ApexTypes.COMMA;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}