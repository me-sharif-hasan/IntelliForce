/*
 * Copyright 2014 Mark Borner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iishanto.language.sf.apex.filetypes;

import com.iishanto.language.sf.lexer.ApexLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.intellij.psi.JavaTokenType.*;

public class ApexSyntaxHighlighter extends SyntaxHighlighterBase {

    private static final Map<IElementType, TextAttributesKey> textAttributeMap;

    static {
        textAttributeMap = new HashMap<>();
        fillMap(textAttributeMap, getKeywordTokens(), DefaultLanguageHighlighterColors.KEYWORD);
        textAttributeMap.put(COMMA, DefaultLanguageHighlighterColors.COMMA);
        textAttributeMap.put(SEMICOLON, DefaultLanguageHighlighterColors.SEMICOLON);
        textAttributeMap.put(DOT, DefaultLanguageHighlighterColors.DOT);
        textAttributeMap.put(CHARACTER_LITERAL, DefaultLanguageHighlighterColors.STRING);
        fillMap(textAttributeMap, TokenSet.create(LBRACE, RBRACE), DefaultLanguageHighlighterColors.BRACES);
        fillMap(textAttributeMap, TokenSet.create(LPARENTH, RPARENTH), DefaultLanguageHighlighterColors.PARENTHESES);
        fillMap(textAttributeMap, TokenSet.create(LBRACKET, RBRACKET), DefaultLanguageHighlighterColors.BRACKETS);
        fillMap(textAttributeMap, TokenSet.create(INTEGER_LITERAL, LONG_LITERAL), DefaultLanguageHighlighterColors.NUMBER);
        textAttributeMap.put(STRING_LITERAL, DefaultLanguageHighlighterColors.STRING);
        textAttributeMap.put(IDENTIFIER, DefaultLanguageHighlighterColors.IDENTIFIER);
        textAttributeMap.put(C_STYLE_COMMENT, DefaultLanguageHighlighterColors.BLOCK_COMMENT);
        textAttributeMap.put(END_OF_LINE_COMMENT, DefaultLanguageHighlighterColors.LINE_COMMENT);
        textAttributeMap.put(TokenType.BAD_CHARACTER, HighlighterColors.BAD_CHARACTER);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new ApexLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        return pack(textAttributeMap.get(tokenType));
    }

    private static TokenSet getKeywordTokens() {
        return TokenSet.create(CLASS_KEYWORD, TRUE_KEYWORD, FALSE_KEYWORD, PRIVATE_KEYWORD, PUBLIC_KEYWORD,
                ABSTRACT_KEYWORD, WITH_KEYWORD, WITH_KEYWORD, EXTENDS_KEYWORD, IMPLEMENTS_KEYWORD, INTERFACE_KEYWORD,
                VOID_KEYWORD, STATIC_KEYWORD, RETURN_KEYWORD, THROW_KEYWORD,
                BREAK_KEYWORD, CONTINUE_KEYWORD, IF_KEYWORD, ELSE_KEYWORD, FOR_KEYWORD, WHILE_KEYWORD, TRY_KEYWORD,
                CATCH_KEYWORD, FINALLY_KEYWORD, FINAL_KEYWORD, SUPER_KEYWORD, THIS_KEYWORD,
                TRANSIENT_KEYWORD, ENUM_KEYWORD, NEW_KEYWORD);
    }
}
