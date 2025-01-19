package com.iishanto.language.sf.apex.psi;

import com.iishanto.language.sf.ApexLanguage;
import com.intellij.psi.tree.IElementType;

public class ApexTokenType extends IElementType {
    public ApexTokenType(String debugName) {
        super(debugName, ApexLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "ApexTokenType." + super.toString();
    }
}
