package com.iishanto.language.sf.parser;

import com.iishanto.language.sf.ApexLanguage;
import com.iishanto.language.sf.apex.psi.ApexClassFile;
import com.iishanto.language.sf.lexer.SfApexLexer;
import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import generated.GeneratedParser;
import generated.GeneratedTypes;
import org.jetbrains.annotations.NotNull;

public class ApexClassParserDefinition implements ParserDefinition {
    private static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    private static final TokenSet COMMENTS = TokenSet.create(GeneratedTypes.COMMENT);

    public static final IFileElementType FILE = new IFileElementType(Language.findInstance(ApexLanguage.class));


    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new SfApexLexer();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new GeneratedParser();
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
        return GeneratedTypes.Factory.createElement(node);
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
