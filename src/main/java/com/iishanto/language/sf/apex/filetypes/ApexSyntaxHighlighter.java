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
import com.iishanto.language.sf.lexer.SfApexLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import generated.GeneratedTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.intellij.psi.JavaTokenType.*;

public class ApexSyntaxHighlighter extends SyntaxHighlighterBase {

    private static final Map<IElementType, TextAttributesKey> textAttributeMap;

    static {
        textAttributeMap = new HashMap<>();
        fillMap(textAttributeMap, getKeywordTokens(), DefaultLanguageHighlighterColors.KEYWORD);
        textAttributeMap.put(GeneratedTypes.COMMA, DefaultLanguageHighlighterColors.COMMA);
        textAttributeMap.put(GeneratedTypes.SEMICOLON, DefaultLanguageHighlighterColors.SEMICOLON);
        textAttributeMap.put(GeneratedTypes.DOT, DefaultLanguageHighlighterColors.DOT);
        textAttributeMap.put(GeneratedTypes.STRING, DefaultLanguageHighlighterColors.STRING);
        fillMap(textAttributeMap, TokenSet.create(GeneratedTypes.LBRACE, GeneratedTypes.RBRACE), DefaultLanguageHighlighterColors.BRACES);
        fillMap(textAttributeMap, TokenSet.create(GeneratedTypes.LPAREN, GeneratedTypes.RPAREN), DefaultLanguageHighlighterColors.PARENTHESES);
        fillMap(textAttributeMap, TokenSet.create(GeneratedTypes.LBRACKET, GeneratedTypes.RBRACKET), DefaultLanguageHighlighterColors.BRACKETS);
        fillMap(textAttributeMap, TokenSet.create(GeneratedTypes.NUMBER), DefaultLanguageHighlighterColors.NUMBER);
        textAttributeMap.put(GeneratedTypes.IDENTIFIER, DefaultLanguageHighlighterColors.IDENTIFIER);
        textAttributeMap.put(GeneratedTypes.COMMENT, DefaultLanguageHighlighterColors.BLOCK_COMMENT);
        textAttributeMap.put(TokenType.BAD_CHARACTER, HighlighterColors.BAD_CHARACTER);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SfApexLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        System.out.println("ii: Token type: " + tokenType);
        return pack(textAttributeMap.get(tokenType));
    }

    private static TokenSet getKeywordTokens() {
        return TokenSet.create(GeneratedTypes.CLASS_KEYWORD, GeneratedTypes.TRUE_LITERAL, GeneratedTypes.FALSE_LITERAL, GeneratedTypes.PRIVATE_KEYWORD, GeneratedTypes.PUBLIC_KEYWORD,
                GeneratedTypes.ABSTRACT_KEYWORD, GeneratedTypes.WITH_KEYWORD, GeneratedTypes.WITH_KEYWORD, GeneratedTypes.EXTENDS_KEYWORD, GeneratedTypes.IMPLEMENTS_KEYWORD, GeneratedTypes.INTERFACE_KEYWORD,
                GeneratedTypes.VOID_KEYWORD, GeneratedTypes.STATIC_KEYWORD, GeneratedTypes.RETURN_KEYWORD, GeneratedTypes.THROW_KEYWORD,
                GeneratedTypes.BREAK_KEYWORD, GeneratedTypes.CONTINUE_KEYWORD, GeneratedTypes.IF_KEYWORD, GeneratedTypes.ELSE_KEYWORD, GeneratedTypes.FOR_KEYWORD, GeneratedTypes.WHILE_KEYWORD, GeneratedTypes.TRY_KEYWORD,
                GeneratedTypes.CATCH_KEYWORD, GeneratedTypes.FINALLY_KEYWORD, GeneratedTypes.FINAL_KEYWORD, GeneratedTypes.SUPER_KEYWORD, GeneratedTypes.THIS_KEYWORD,
                GeneratedTypes.ENUM_KEYWORD, GeneratedTypes.NEW_KEYWORD);
    }
}
