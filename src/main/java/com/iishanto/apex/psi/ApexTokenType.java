package com.iishanto.apex.psi;

import com.iishanto.ApexLanguage;
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
