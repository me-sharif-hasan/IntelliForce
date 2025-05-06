package com.iishanto.language.sf.lexer;

import com.iishanto.language.sf.grammer._RefinedApexGrammmerLexer;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;
import com.intellij.lexer.LayeredLexer;
import org.jetbrains.annotations.NotNull;

public class SfApexLexer extends FlexAdapter {
    public SfApexLexer() {
        super(new _RefinedApexGrammmerLexer());
    }
}
