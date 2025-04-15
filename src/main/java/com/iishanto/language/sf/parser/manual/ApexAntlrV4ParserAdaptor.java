package com.iishanto.language.sf.parser.manual;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;

public class ApexAntlrV4ParserAdaptor extends Parser {
    org.antlr.runtime.Parser parser;
    public ApexAntlrV4ParserAdaptor(TokenStream input, org.antlr.runtime.Parser parser) {
        super(input);
        this.parser = parser;
    }

    @Override
    public String[] getTokenNames() {
        return parser.getTokenNames();
    }

    @Override
    public String[] getRuleNames() {
        return new String[0];
    }

    @Override
    public String getGrammarFileName() {
        return parser.getGrammarFileName();
    }

    @Override
    public ATN getATN() {
        return null;
    }
}
