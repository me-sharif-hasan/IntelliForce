package com.iishanto.language.sf.parser;


import com.iishanto.language.sf.ApexLanguage;
import com.iishanto.language.sf.lexer.ApexLexer;
import com.illuminatedcloud.intellij.apex.fileType.ApexClassFile;
import com.illuminatedcloud.intellij.apex.psi.ApexTypes;
import com.illuminatedcloud.intellij.apex.psi.ApexTypes$Factory;
import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class JetforceParserDefinition implements ParserDefinition {
    private static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    private static final TokenSet COMMENTS = TokenSet.create(ApexTypes.LINE_COMMENT, ApexTypes.BLOCK_COMMENT);

    public static final IFileElementType FILE = new IFileElementType(Language.findInstance(ApexLanguage.class));


    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new ApexLexer();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
//        return null;
        return new PsiParser() {
            @Override
            public @NotNull ASTNode parse(@NotNull IElementType iElementType, @NotNull PsiBuilder psiBuilder) {
                return null;
            }
        };
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
        return ApexTypes$Factory.createElement(node);
//        return new ANTLRPsiNode(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new ApexClassFile(viewProvider,null);
    }

    @Override
    public @NotNull SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
