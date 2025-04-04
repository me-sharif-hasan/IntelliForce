package com.iishanto.language.sf.lexer;

import com.iishanto.language.sf.grammer._ApexLexer;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;
import com.intellij.lexer.Lexer;
import org.jetbrains.annotations.NotNull;

public class BnfApexLexer extends FlexAdapter {
    public BnfApexLexer() {
        super(new _ApexLexer());
    }
}
