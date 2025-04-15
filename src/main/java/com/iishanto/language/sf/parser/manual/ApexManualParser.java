package com.iishanto.language.sf.parser.manual;

import apex.jorje.parser.impl.ApexLexer;
import apex.jorje.parser.impl.ApexParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.iishanto.language.sf.apex.psi.ApexTokenType;
import com.iishanto.language.sf.apex.psi.ApexTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.tree.IElementType;
import org.antlr.runtime.BufferedTokenStream;
import org.antlr.runtime.TokenStream;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ApexManualParser implements PsiParser {
    Project project;
    private Map<IElementType,String> markings;
    public ApexManualParser(Project project) {
        this.project = project;
    }
    private boolean isAlphabetical(char c){
        if(c>='a' && c<='z')return true;
        if(c>='A' && c<='Z')return true;
        if(c=='_')return true;
        if(c=='$')return true;
        return false;
    }

    public void antlrApexGrammer(){
        ApexLexer apexLexer = new ApexLexer();
        apexLexer.setText("public class Test { public void test() { // comment\n" +
                "int a = 0; /* comment */\n" +
                "String s = \"Hello\";\n" +
                "}\n" +
                "}");
        TokenStream tokenStream=new BufferedTokenStream(apexLexer);
        ApexParser parser = new ApexParser(tokenStream);
        try{
//            parser.compilationUnit().;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public @NotNull ASTNode parse(@NotNull IElementType iElementType, @NotNull PsiBuilder psiBuilder) {
        Stack < PsiBuilder.Marker > markers = new Stack <>();
        StringBuilder source= new StringBuilder();
        char lastChar=' ';
        while (!psiBuilder.eof()){
            String tokenText = psiBuilder.getTokenText();
            markers.push(psiBuilder.mark());
            IElementType tokenType = psiBuilder.getTokenType();
            psiBuilder.advanceLexer();

            if(tokenText==null) continue;
            if(!(tokenType==JavaTokenType.C_STYLE_COMMENT||tokenType==JavaTokenType.END_OF_LINE_COMMENT)){
                char tokenFirstChar=tokenText.charAt(0);
                if(isAlphabetical(tokenFirstChar)&&isAlphabetical(lastChar)) {
                    source.append(" ");
                }
                source.append(tokenText);
            }
            lastChar=tokenText.charAt(tokenText.length()-1);
        }
        while (!markers.isEmpty()){
            PsiBuilder.Marker marker = markers.pop();
            marker.rollbackTo();
        }
        Apex apex= Apex.getInstance();
        System.out.println("Parsing " + source);
        String astJson = apex.getAstJson(source.toString());
        System.out.println("Parsing " + source + " done");
        JsonElement parsedJsonElement= new Gson().fromJson(astJson, JsonElement.class);
        System.out.println("Parsed " + parsedJsonElement);
        PsiBuilder.Marker marker = psiBuilder.mark();
        marker.done(iElementType);
        return psiBuilder.getTreeBuilt();
    }

    private List<IElementType> getLookAheadTokenStream(PsiBuilder psiBuilder) {
        System.out.println("Getting look ahead token stream" + psiBuilder.getTokenType() + " " + psiBuilder.getCurrentOffset() + " " + psiBuilder.eof());
        List<IElementType> tokens = new java.util.ArrayList<>();
        int lookahead = 0;
        while (true) {
            IElementType tokenType = psiBuilder.lookAhead(lookahead);
            if (tokenType == null) {
                break;
            }
            tokens.add(tokenType);
            lookahead++;
        }
        return tokens;
    }
}
