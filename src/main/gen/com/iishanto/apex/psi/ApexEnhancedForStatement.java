// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexEnhancedForStatement extends PsiElement {

  @NotNull
  ApexClassInterfaceOrPrimitiveType getClassInterfaceOrPrimitiveType();

  @Nullable
  ApexExpression getExpression();

  @Nullable
  ApexStatement getStatement();

  @Nullable
  ApexVariableModifier getVariableModifier();

  @NotNull
  PsiElement getIdentifier();

}
