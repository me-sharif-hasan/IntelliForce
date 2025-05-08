package com.iishanto.language.sf.apex.filetypes;

import com.iishanto.language.sf.apex.psi.ApexTypes;
import com.iishanto.language.sf.lexer.ApexLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MyApexSyntaxHighlighterFactory extends SyntaxHighlighterBase {
    static Map<IElementType, TextAttributesKey> map = new HashMap<IElementType, TextAttributesKey>();
    static {
        fillMap(map,getKeywordTokens(), DefaultLanguageHighlighterColors.KEYWORD);
    }
    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ApexLexer();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType iElementType) {
        return pack(map.get(iElementType));
    }

    private static TokenSet getKeywordTokens() {
        return TokenSet.create(ApexTypes.CLASS_KEYWORD, ApexTypes.TRUE_LITERAL, ApexTypes.FALSE_LITERAL, ApexTypes.PRIVATE_KEYWORD, ApexTypes.PUBLIC_KEYWORD,
                ApexTypes.ABSTRACT_KEYWORD, ApexTypes.WITH_KEYWORD, ApexTypes.WITH_KEYWORD, ApexTypes.EXTENDS_KEYWORD, ApexTypes.IMPLEMENTS_KEYWORD, ApexTypes.INTERFACE_KEYWORD,
                ApexTypes.VOID_KEYWORD, ApexTypes.STATIC_KEYWORD, ApexTypes.RETURN_KEYWORD, ApexTypes.THROW_KEYWORD,
                ApexTypes.BREAK_KEYWORD, ApexTypes.CONTINUE_KEYWORD, ApexTypes.IF_KEYWORD, ApexTypes.ELSE_KEYWORD, ApexTypes.FOR_KEYWORD, ApexTypes.WHILE_KEYWORD, ApexTypes.TRY_KEYWORD,
                ApexTypes.CATCH_KEYWORD, ApexTypes.FINALLY_KEYWORD, ApexTypes.FINAL_KEYWORD, ApexTypes.SUPER_KEYWORD, ApexTypes.THIS_KEYWORD,
                ApexTypes.ENUM_KEYWORD, ApexTypes.NEW_KEYWORD);
    }
}
