// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexVariableDefinition extends PsiElement {

  @Nullable
  ApexVariableInitializer getVariableInitializer();

  @NotNull
  PsiElement getIdentifier();

}
