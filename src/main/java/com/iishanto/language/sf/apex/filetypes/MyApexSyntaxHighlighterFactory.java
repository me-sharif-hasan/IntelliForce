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
        map.put(ApexTypes.NUMBER, DefaultLanguageHighlighterColors.NUMBER);
        map.put(ApexTypes.STRING, DefaultLanguageHighlighterColors.STRING);
        map.put(ApexTypes.IDENTIFIER, DefaultLanguageHighlighterColors.IDENTIFIER);
        map.put(ApexTypes.COMMA, DefaultLanguageHighlighterColors.COMMA);
        map.put(ApexTypes.SEMICOLON, DefaultLanguageHighlighterColors.SEMICOLON);
        map.put(ApexTypes.DOT, DefaultLanguageHighlighterColors.DOT);
        fillMap(map, TokenSet.create(ApexTypes.LBRACE, ApexTypes.RBRACE), DefaultLanguageHighlighterColors.BRACES);
        fillMap(map, TokenSet.create(ApexTypes.LBRACE, ApexTypes.RBRACE), DefaultLanguageHighlighterColors.BRACES);
        fillMap(map, TokenSet.create(ApexTypes.LPAREN, ApexTypes.RPAREN), DefaultLanguageHighlighterColors.PARENTHESES);
        fillMap(map, TokenSet.create(ApexTypes.LBRACKET, ApexTypes.RBRACKET), DefaultLanguageHighlighterColors.BRACKETS);
        map.put(ApexTypes.COMMENT, DefaultLanguageHighlighterColors.LINE_COMMENT);
        map.put(ApexTypes.BLOCK_COMMENT, DefaultLanguageHighlighterColors.BLOCK_COMMENT);
        //for if else switch when etc
        fillMap(map,getControlFlowTokens(), DefaultLanguageHighlighterColors.CONSTANT);
    }
    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ApexLexer();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType iElementType) {
        return pack(map.get(iElementType));
    }

    private static TokenSet getControlFlowTokens(){
        return TokenSet.create(
                ApexTypes.IF_KEYWORD, ApexTypes.ELSE_KEYWORD, ApexTypes.SWITCH_KEYWORD, ApexTypes.CASE_KEYWORD,
                ApexTypes.DEFAULT_KEYWORD, ApexTypes.WHILE_KEYWORD, ApexTypes.FOR_KEYWORD, ApexTypes.DO_KEYWORD,
                ApexTypes.BREAK_KEYWORD, ApexTypes.CONTINUE_KEYWORD, ApexTypes.RETURN_KEYWORD, ApexTypes.THROW_KEYWORD,
                ApexTypes.TRY_KEYWORD, ApexTypes.CATCH_KEYWORD, ApexTypes.FINALLY_KEYWORD, ApexTypes.THROW_KEYWORD
        );
    }
    private static TokenSet getKeywordTokens() {
        return TokenSet.create(ApexTypes.CLASS_KEYWORD, ApexTypes.TRUE_LITERAL, ApexTypes.FALSE_LITERAL, ApexTypes.PRIVATE_KEYWORD, ApexTypes.PUBLIC_KEYWORD,
                ApexTypes.ABSTRACT_KEYWORD, ApexTypes.WITH_KEYWORD, ApexTypes.WITH_KEYWORD, ApexTypes.EXTENDS_KEYWORD, ApexTypes.IMPLEMENTS_KEYWORD, ApexTypes.INTERFACE_KEYWORD,
                ApexTypes.VOID_KEYWORD, ApexTypes.STATIC_KEYWORD, ApexTypes.RETURN_KEYWORD, ApexTypes.THROW_KEYWORD,ApexTypes.INSERT_KEYWORD, ApexTypes.DELETE_KEYWORD, ApexTypes.UPDATE_KEYWORD, ApexTypes.UPSERT_KEYWORD,
                ApexTypes.MERGE_KEYWORD,
                ApexTypes.BREAK_KEYWORD, ApexTypes.CONTINUE_KEYWORD, ApexTypes.IF_KEYWORD, ApexTypes.ELSE_KEYWORD, ApexTypes.FOR_KEYWORD, ApexTypes.WHILE_KEYWORD, ApexTypes.TRY_KEYWORD,
                ApexTypes.CATCH_KEYWORD, ApexTypes.FINALLY_KEYWORD, ApexTypes.FINAL_KEYWORD, ApexTypes.SUPER_KEYWORD, ApexTypes.THIS_KEYWORD,
                ApexTypes.ENUM_KEYWORD, ApexTypes.NEW_KEYWORD);
    }
}
