package com.iishanto.apex.psi;

import com.iishanto.ApexLanguage;
import com.intellij.psi.tree.IElementType;

public class ApexElementType extends IElementType {

    public ApexElementType(String debugName) {
        super(debugName, ApexLanguage.INSTANCE);
    }
}
