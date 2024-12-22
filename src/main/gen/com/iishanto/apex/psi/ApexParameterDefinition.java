// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexParameterDefinition extends PsiElement {

  @NotNull
  ApexClassInterfaceOrPrimitiveType getClassInterfaceOrPrimitiveType();

  @Nullable
  ApexVariableModifier getVariableModifier();

  @NotNull
  PsiElement getIdentifier();

}
