package com.iishanto.language.sf.parser;

//import com.iishanto.antlr.ApexLexer;
//import com.iishanto.antlr.ApexParser;
import com.iishanto.antlr.ApexParser;
import com.iishanto.language.sf.ApexLanguage;
//import com.iishanto.language.sf.apex.psi.ApexTypes;
//import com.iishanto.language.sf.apex.psi.ApexTypes;
import com.iishanto.language.sf.lexer.BnfApexLexer;
import com.iishanto.language.sf.parser.manual.ApexManualParser;
import com.illuminatedcloud.intellij.apex.fileType.ApexClassFile;
import com.illuminatedcloud.intellij.apex.lexer.ApexLexer;
import com.illuminatedcloud.intellij.apex.psi.ApexType;
import com.illuminatedcloud.intellij.apex.psi.ApexTypes;
import com.illuminatedcloud.intellij.apex.psi.ApexTypes$Factory;
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
    private static final TokenSet COMMENTS = TokenSet.create(ApexTypes.LINE_COMMENT, ApexTypes.BLOCK_COMMENT);

    public static final IFileElementType FILE = new IFileElementType(Language.findInstance(ApexLanguage.class));

    public static void main(String[] args) {
//        new com.illuminatedcloud.intellij.apex.lexer.ApexLexer();
//        new com.illuminatedcloud.intellij.apex.parser.ApexParser();

    }

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new ApexLexer();
//        String sourceCode = "public class Test {}";
//        CharStream charStream = CharStreams.fromString(sourceCode);
//        ApexLexer apexLexer=new ApexLexer(null);
//        return new ANTLRLexerAdaptor(ApexLanguage.INSTANCE, apexLexer);
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new com.iishanto.language.sf.apex.parser.ApexParser();
//        ApexParser apexParser = new ApexParser(null);
//        return new ANTLRParserAdaptor(ApexLanguage.INSTANCE, apexParser) {
//            @Override
//            protected ParseTree parse(Parser parser, IElementType iElementType) {
//                System.out.println("Parse called " + parser.getClass().getName() + " " + iElementType.toString());
//                if ( iElementType instanceof IFileElementType ) {
//                    return ((ApexParser) parser).primary();
//                }
//                return ((ApexParser)parser).primary();
//            }
//        };
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
