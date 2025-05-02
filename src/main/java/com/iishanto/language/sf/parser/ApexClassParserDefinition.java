package com.iishanto.language.sf.parser;

import com.iishanto.language.sf.ApexLanguage;
import com.iishanto.language.sf.apex.psi.ApexClassFile;
import com.iishanto.language.sf.apex.psi.ApexTypes;
import com.iishanto.language.sf.lexer.ApexLexer;
import com.iishanto.language.sf.lexer.BnfApexLexer;
import com.intellij.lang.*;
import com.intellij.lang.java.lexer.JavaLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;

public class ApexClassParserDefinition implements ParserDefinition {
    private static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    private static final TokenSet COMMENTS = TokenSet.create(ApexTypes.LINE_COMMENT, ApexTypes.COMMENT);

    public static final IFileElementType FILE = new IFileElementType(Language.findInstance(ApexLanguage.class));


    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new ApexLexer();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new com.iishanto.language.sf.apex.parser.ApexParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return ApexTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new ApexClassFile(viewProvider);
    }

    @Override
    public @NotNull SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
