package com.iishanto;

import com.iishanto.grammer.ApexLexer;
import com.iishanto.grammer.ApexParser;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.psi.tree.IElementType;
import groovyjarjarantlr4.runtime.ANTLRInputStream;
import groovyjarjarantlr4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
