// license.txt
package com.iishanto.apex.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ApexLocalVariableDeclarator extends PsiElement {

  @NotNull
  ApexClassInterfaceOrPrimitiveType getClassInterfaceOrPrimitiveType();

  @NotNull
  List<ApexVariableDefinition> getVariableDefinitionList();

  @Nullable
  ApexVariableModifier getVariableModifier();

}
