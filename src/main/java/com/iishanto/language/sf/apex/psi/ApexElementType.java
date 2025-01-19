package com.iishanto.language.sf.apex.psi;

import com.iishanto.language.sf.ApexLanguage;
import com.intellij.psi.tree.IElementType;

public class ApexElementType extends IElementType {

    public ApexElementType(String debugName) {
        super(debugName, ApexLanguage.INSTANCE);
    }
}
