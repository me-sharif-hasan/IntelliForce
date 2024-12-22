// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexCatchClause extends PsiElement {

  @Nullable
  ApexBlock getBlock();

  @Nullable
  ApexClassOrInterfaceReference getClassOrInterfaceReference();

  @Nullable
  ApexVariableModifier getVariableModifier();

  @Nullable
  PsiElement getIdentifier();

}
